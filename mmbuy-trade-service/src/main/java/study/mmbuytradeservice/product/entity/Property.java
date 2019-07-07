package study.mmbuytradeservice.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "t_property")
public class Property implements Serializable {

    private Long id;

    private Long categoryId;

    private String propertyName;

    private Date createTime;

    private Date updateTime;
}
