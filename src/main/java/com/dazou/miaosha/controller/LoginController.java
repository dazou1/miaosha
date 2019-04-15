package com.dazou.miaosha.controller;

import com.dazou.miaosha.redis.RedisService;
import com.dazou.miaosha.result.CodeMsg;
import com.dazou.miaosha.result.Result;
import com.dazou.miaosha.service.MiaoshaUserService;
import com.dazou.miaosha.service.UserService;
import com.dazou.miaosha.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 16:45 2019-04-08
 */

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {


    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    //使用@Valid注解进行参数校验，减少代码冗余度
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());

        //登录
        String token = miaoshaUserService.login(response, loginVo);

        return Result.success(token);
    }
}
