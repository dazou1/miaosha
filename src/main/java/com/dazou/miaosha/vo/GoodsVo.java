package com.dazou.miaosha.vo;

import com.dazou.miaosha.domain.Goods;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:47 2019-04-11
 */

@Getter
@Setter
public class GoodsVo extends Goods {

    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
