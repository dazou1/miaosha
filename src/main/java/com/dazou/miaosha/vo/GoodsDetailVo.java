package com.dazou.miaosha.vo;

import com.dazou.miaosha.domain.MiaoshaUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 13:14 2019-04-14
 */

@Getter
@Setter
public class GoodsDetailVo {

    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods ;
    private MiaoshaUser user;

}
