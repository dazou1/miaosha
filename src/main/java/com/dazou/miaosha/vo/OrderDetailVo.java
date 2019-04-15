package com.dazou.miaosha.vo;

import com.dazou.miaosha.domain.OrderInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 13:12 2019-04-14
 */

@Setter
@Getter
public class OrderDetailVo {

    private GoodsVo goods;
    private OrderInfo order;

}
