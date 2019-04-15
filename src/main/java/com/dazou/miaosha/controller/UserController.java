package com.dazou.miaosha.controller;

import com.dazou.miaosha.domain.MiaoshaUser;
import com.dazou.miaosha.redis.RedisService;
import com.dazou.miaosha.result.Result;
import com.dazou.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 13:09 2019-04-14
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }

}
