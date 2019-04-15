package com.dazou.miaosha.redis;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 11:11 2019-04-08
 */
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
