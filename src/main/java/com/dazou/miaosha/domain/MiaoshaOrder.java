package com.dazou.miaosha.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:38 2019-04-11
 */

@Setter
@Getter
public class MiaoshaOrder {

    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;

}
