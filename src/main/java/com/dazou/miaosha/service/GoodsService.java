package com.dazou.miaosha.service;

import com.dazou.miaosha.dao.GoodsDao;
import com.dazou.miaosha.domain.MiaoshaGoods;
import com.dazou.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:46 2019-04-11
 */

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        int res = goodsDao.reduceStock(g);
        return res > 0;
    }

    public void resetStock(List<GoodsVo> goodsList) {
        for (GoodsVo goods : goodsList) {
            MiaoshaGoods g = new MiaoshaGoods();
            g.setGoodsId(goods.getId());
            g.setStockCount(goods.getStockCount());
            goodsDao.resetStock(g);
        }
    }
}
