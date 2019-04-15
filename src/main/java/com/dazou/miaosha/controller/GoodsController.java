package com.dazou.miaosha.controller;

import com.dazou.miaosha.domain.Goods;
import com.dazou.miaosha.domain.MiaoshaUser;
import com.dazou.miaosha.redis.GoodsKey;
import com.dazou.miaosha.redis.RedisService;
import com.dazou.miaosha.result.Result;
import com.dazou.miaosha.service.GoodsService;
import com.dazou.miaosha.service.MiaoshaUserService;
import com.dazou.miaosha.vo.GoodsDetailVo;
import com.dazou.miaosha.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 20:01 2019-04-08
 */

@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    //@CookieValue取的是cookie中的token值
    //@RequestParam取的是传递参数中的token值
//    @RequestMapping("/to_list")
//    public String list(HttpServletResponse response, Model model,
//                          @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false) String cookieToken,
//                          @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false) String paramToken) {
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//            return "login";
//        }
//
//        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
//
//        MiaoshaUser user = miaoshaUserService.getByToken(response, token);
//
//        model.addAttribute("user", user);
//
//        return "goods_list";
//    }


//    //利用参数解释器进行参数校验
//    @RequestMapping("/to_list")
//    public String list(Model model, MiaoshaUser user) {
//
//        model.addAttribute("user", user);
//
//        //查询商品列表
//        List<GoodsVo> goodsList= goodsService.listGoodsVo();
//        model.addAttribute("goodsList", goodsList);
//        return "goods_list";
//    }

    @RequestMapping("/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response, Model model,MiaoshaUser user,
                                      @PathVariable("goodsId")long goodsId) {

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoods(goods);
        vo.setUser(user);
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
        return Result.success(vo);
    }

    /**
     * QPS:1267 load:15 mysql
     * 5000 * 10
     * QPS:2884, load:5
     * */
    @RequestMapping(value="/to_list", produces="text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
//    	 return "goods_list";
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;
    }

    @RequestMapping(value="/to_detail2/{goodsId}",produces="text/html")
    @ResponseBody
    public String detail2(HttpServletRequest request, HttpServletResponse response, Model model,MiaoshaUser user,
                          @PathVariable("goodsId")long goodsId) {
        model.addAttribute("user", user);

        //取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }
        //手动渲染
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
//        return "goods_detail";

        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
        }
        return html;
    }
}
