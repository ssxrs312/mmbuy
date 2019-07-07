package study.mmbuytradeservice.trade.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShoppingCart implements Serializable {

    private Long id;

    private Long userUuid;

    private Long skuId;

    private String skuName;

    private Byte checkStatus;

    private Date createTime;

    private Date updateTime;
}
