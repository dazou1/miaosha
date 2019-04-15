package com.dazou.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 17:06 2019-04-08
 */
public class ValidatorUtil {

    public static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }

        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("15207140148"));
        System.out.println(isMobile("1111111111111"));
    }
}
