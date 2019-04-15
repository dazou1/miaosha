package com.dazou.miaosha.util;

import java.util.UUID;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 19:51 2019-04-08
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
