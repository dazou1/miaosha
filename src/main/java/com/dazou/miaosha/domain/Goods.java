package com.dazou.miaosha.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:40 2019-04-11
 */

@Getter
@Setter
public class Goods {

    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;

}
