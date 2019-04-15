package com.dazou.miaosha.config;

import com.dazou.miaosha.access.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 20:48 2019-04-08
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    UserArgumentResolver userArgumentResolver;

    @Autowired
    AccessInterceptor accessInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    //注册拦截器，使拦截器生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor);
    }
}
