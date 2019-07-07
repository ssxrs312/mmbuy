package study.mmbuytradeservice.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "t_sku_property_option")
public class SkuPropertyOption implements Serializable {

    private Long id;

    private Long skuId;

    private Long propertyId;

    private Long propertyOptionId;

    private Byte enableFlag;

    private Date createTime;

    private Date updateTime;
}
