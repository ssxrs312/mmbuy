package study.mmbuystockservice.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import study.mmbuystockservice.stock.entity.Stock;

public interface StockMapper extends BaseMapper<Stock> {

//    @Select("select * from t_stock where skuId = #{skuid}")
//    Stock selectBySkuId(@Param("skuid") long skuId);
}
