package com.dazou.miaosha.controller;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 13:10 2019-04-14
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dazou.miaosha.domain.MiaoshaUser;
import com.dazou.miaosha.domain.OrderInfo;
import com.dazou.miaosha.redis.RedisService;
import com.dazou.miaosha.result.CodeMsg;
import com.dazou.miaosha.result.Result;
import com.dazou.miaosha.service.GoodsService;
import com.dazou.miaosha.service.MiaoshaUserService;
import com.dazou.miaosha.service.OrderService;
import com.dazou.miaosha.vo.GoodsVo;
import com.dazou.miaosha.vo.OrderDetailVo;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model,MiaoshaUser user,
                                      @RequestParam("orderId") long orderId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }

}

