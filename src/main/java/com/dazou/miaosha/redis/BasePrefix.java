package com.dazou.miaosha.redis;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 11:12 2019-04-08
 */
public abstract class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    //0代表永不过期
    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }


}
