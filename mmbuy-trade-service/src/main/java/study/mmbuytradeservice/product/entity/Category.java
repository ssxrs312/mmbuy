package study.mmbuytradeservice.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName(value = "t_category")  //实体类名跟数据库表名不一样
public class Category implements Serializable {

    private Long id;

    private Long parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

    @TableField(exist=false)   //该字段在数据库表中没有
    private List<Category> children;

}
