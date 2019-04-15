package com.dazou.miaosha.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:37 2019-04-11
 */

@Getter
@Setter
public class OrderInfo {

    private Long id;
    private Long userId;
    private Long goodsId;
    private Long  deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;

}
