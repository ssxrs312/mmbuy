package study.mmbuystockservice.stock.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockReduce implements Serializable {

    private Long orderNo;

    private Long skuId;

    private Integer reduceCount; //正数为增加/释放库存  负数为扣减
}
