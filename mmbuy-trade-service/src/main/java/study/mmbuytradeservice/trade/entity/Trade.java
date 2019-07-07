package study.mmbuytradeservice.trade.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Trade implements Serializable {

    private Long id;

    private Long tradeNo;

    private Long userUuid;

    private BigDecimal payment;

    private Byte paymenyType;

    private Byte status;

    private Date paymentTime;

    private Date closeTime;

    private Date createTime;

    private Date updateTime;
}
