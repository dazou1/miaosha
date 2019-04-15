package com.dazou.miaosha.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:39 2019-04-11
 */

@Getter
@Setter
public class MiaoshaGoods {

    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
