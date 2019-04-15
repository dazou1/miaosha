package com.dazou.miaosha.controller;

import com.dazou.miaosha.domain.User;
import com.dazou.miaosha.rabbitmq.MQSender;
import com.dazou.miaosha.redis.RedisService;
import com.dazou.miaosha.redis.UserKey;
import com.dazou.miaosha.result.CodeMsg;
import com.dazou.miaosha.result.Result;

import com.dazou.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:08 2019-04-08
 */

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;

    @Autowired
    MQSender mqSender;

//    @RequestMapping("/mq")
//    @ResponseBody
//    public Result<String> mq() {
//        mqSender.send("hello,dazou");
//        return Result.success("hello,world");
//    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello,world");
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.error(CodeMsg.SESSION_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "dazou");
        return "hello";
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("dazou");
        redisService.set(UserKey.getById, "" + 1, user);
        return Result.success(true);
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }
}
