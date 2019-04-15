package com.dazou.miaosha.redis;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 11:18 2019-04-08
 */
public class UserKey extends BasePrefix {

    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
