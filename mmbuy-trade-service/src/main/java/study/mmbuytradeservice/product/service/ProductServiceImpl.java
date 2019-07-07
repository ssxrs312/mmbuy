package study.mmbuytradeservice.product.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.searchbox.client.http.JestHttpClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import study.mmbuytradeservice.common.constants.Constants;
import study.mmbuytradeservice.product.entity.Category;
import study.mmbuytradeservice.product.entity.Product;
import study.mmbuytradeservice.product.mapper.CategoryMapper;
import study.mmbuytradeservice.product.mapper.ProductMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    int PAGE_SIZE = 15; //默认分页大小

    int PAGE_NUMBER = 0; //默认当前分页

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private JestHttpClient esClient;


    /**
     * ES全文检索
     */
    @Override
    public List<Product> search(int pageNumber, int pageSize, String searchContent) throws IOException {
        if(pageSize==0) {
            pageSize = PAGE_SIZE;
        }
        if(pageNumber<=0) {
            pageNumber = PAGE_NUMBER;
        }

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //查询，是一种链式的风格，很容易拼接，就是拼接根据上面字段进行匹配，并且支持分页
        searchSourceBuilder.query(QueryBuilders//组合查询
                .boolQuery()
                .must(QueryBuilders.matchQuery("spu_name",searchContent))//must：必须匹配，相当于and；should：相当于or；must not：不匹配
                .must(QueryBuilders.matchQuery("status",1)))//第二个条件是必须是在架的商品
                .from(pageNumber*pageSize)//分页展示的起始位置
                .size(pageSize);//每页展示多少

        //这里就是高亮显示的设置
        searchSourceBuilder.highlight()
                .field("spu_name")
                .preTags("<em>").postTags("</em>")
                .fragmentSize(200);

        //索引，根据索引来查的，我们在logstash中已经设置了这个索引名称
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("jdbc")
                .build();
        SearchResult response = esClient.execute(search);
        String jsonString = response.getJsonString();
        List<Product> productList = parseResult(jsonString);

        return productList;
    }

    @Override
    @Cacheable(cacheNames = Constants.CACHE_PRODUCT_CATEGORY)
    public List<Category> listCategory() {
//        return categoryMapper.selectAll();
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<Category>());
        return categories;
    }

    @Override
    public Product productDetail(Long id) {
//        Product product = productMapper.selectByPrimaryKeyWithSku(id);
        Product product = productMapper.selectById(id);
        return product;
    }

    private List<Product> parseResult(String jsonString) {
        JSONArray jsonArray = JSON.parseObject(jsonString).getJSONObject("hits").getJSONArray("hits");
        List<Product> productList = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject productObj = jsonObject.getJSONObject("_source");
            Product product = new Product();
            product.setId(productObj.getLongValue("id"));
            product.setCategoryId(productObj.getLongValue("category_id"));
            product.setBrandId(productObj.getLongValue("brand_id"));
            product.setSpuName(productObj.getString("spu_name"));
            product.setPrice(productObj.getBigDecimal("price"));

            productList.add(product);
        }
        return productList;
    }
}
