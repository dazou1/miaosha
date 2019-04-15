package com.dazou.miaosha.service;

import com.dazou.miaosha.dao.OrderDao;
import com.dazou.miaosha.domain.MiaoshaOrder;
import com.dazou.miaosha.domain.MiaoshaUser;
import com.dazou.miaosha.domain.OrderInfo;
import com.dazou.miaosha.redis.OrderKey;
import com.dazou.miaosha.redis.RedisService;
import com.dazou.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 12:34 2019-04-11
 */
@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    RedisService redisService;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
//        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        // 从缓存中查询订单
        return redisService.get(OrderKey.getMiaoshaOrderByUidGid, "" + userId + "_" + goodsId, MiaoshaOrder.class);
    }

    public OrderInfo getOrderById(long orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Transactional //保证事务原子性
    public OrderInfo creatOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(miaoshaOrder);

        //订单创建成果后，将订单放在缓存中，加快订单查询
        redisService.set(OrderKey.getMiaoshaOrderByUidGid, "" + user.getId() + "_" + goods.getId(), miaoshaOrder);

        return orderInfo;
    }


    public void deleteOrders() {
        orderDao.deleteOrders();
        orderDao.deleteMiaoshaOrders();
    }
}
