package study.mmbuytradeservice.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@TableName(value = "t_sku")
public class ProductSku implements Serializable {

    private Long id;

    private Long spuId;

    private String skuName;

    private BigDecimal skuPrice;

    private String imgUrl;

    private byte enableFlag;

    private byte status;

    private List<SkuPropertyOption> skuPropertyOptions;

    private Date createTime;

    private Date updateTime;
}
