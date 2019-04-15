package com.dazou.miaosha.rabbitmq;

import com.dazou.miaosha.domain.MiaoshaUser;
import com.dazou.miaosha.redis.RedisService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 16:11 2019-04-14
 */
@Slf4j
@Setter
@Getter
public class MiaoshaMessage {

    private MiaoshaUser user;
    private long goodsId;

}
