package com.dazou.miaosha.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:57 2019-04-08
 */
@Component
@ConfigurationProperties(prefix = "redis")
@Getter
@Setter
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;//秒
    private String password;
    private int poolMaxIdle;
    private int poolMaxTotal;
    private int poolMaxWait;//秒
}
