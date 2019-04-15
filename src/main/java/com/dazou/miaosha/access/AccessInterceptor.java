package com.dazou.miaosha.access;

import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.dazou.miaosha.domain.MiaoshaUser;
import com.dazou.miaosha.redis.AccessKey;
import com.dazou.miaosha.redis.RedisService;
import com.dazou.miaosha.result.CodeMsg;
import com.dazou.miaosha.result.Result;
import com.dazou.miaosha.service.MiaoshaUserService;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 20:37 2019-04-14
 */


//拦截器
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(handler instanceof HandlerMethod) {
            MiaoshaUser user = getUser(request, response);
            UserContext.setUser(user);
            HandlerMethod hm = (HandlerMethod)handler;
            //拦截AccessLimit注解器
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if(needLogin) {
                if(user == null) {
                    render(response, CodeMsg.SESSION_ERROR);
                    return false;
                }
                key += "_" + user.getId();
            }else {
                //do nothing
            }
            AccessKey ak = AccessKey.withExpire(seconds);
            //查看用户在一定时间内的抢购次数
            Integer count = redisService.get(ak, key, Integer.class);
            if(count  == null) {//还没进行抢购过，此时就设置为抢购一次
                redisService.set(ak, key, 1);
            }else if(count < maxCount) {//次数还未达到限制次数，增加一次抢购次数
                redisService.incr(ak, key);
            }else {//达到一定时间内的抢购次数限制
                render(response, CodeMsg.ACCESS_LIMIT_REACHED);
                return false;
            }
        }
        return true;
    }

    //返回失败时的提醒信息
    private void render(HttpServletResponse response, CodeMsg cm)throws Exception {
        response.setContentType("application/json;charset=UTF-8");//处理前端乱码问题
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString(Result.error(cm));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

    //从登录信息中获取用户
    private MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(MiaoshaUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, MiaoshaUserService.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return userService.getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[]  cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
