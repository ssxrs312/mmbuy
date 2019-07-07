package study.mmbuytradeservice.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "t_property_option")
public class PropertyOption implements Serializable {

    private Long id;

    private Long propertyId;

    private String optionName;

    private Date createTime;

    private Date updateTime;
}
