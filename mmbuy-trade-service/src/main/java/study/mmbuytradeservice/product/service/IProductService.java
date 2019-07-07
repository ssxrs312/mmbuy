package study.mmbuytradeservice.product.service;

import study.mmbuytradeservice.product.entity.Category;
import study.mmbuytradeservice.product.entity.Product;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    List<Product> search(int pageNumber, int pageSize, String searchContent) throws IOException;

    List<Category> listCategory();

    Product productDetail(Long id);

}
