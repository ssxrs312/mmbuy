package study.mmbuystockservice.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "t_stock")
public class Stock implements Serializable {

    private Long id;

    private Long skuId;

    private Integer stock;

    private Integer lockStock;

    private Date createTime;

    private Date updateTime;
}
