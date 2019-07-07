package study.mmbuytradeservice.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "t_brand")
public class Brand implements Serializable {

    private Long id;

    private String brandName;

    private Date createTime;

    private Date updateTime;
}
