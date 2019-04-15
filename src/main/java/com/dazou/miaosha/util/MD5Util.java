package com.dazou.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 16:30 2019-04-08
 */
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static final String salt = "1a2b3c4d";

    //第一层MD5,用户输入的明文密码进行第一次md5
    public static String inputPassFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //第二层md5,存入到数据库中，使用随机salt
    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass, String salt) {
        return formPassToDBPass(inputPassFormPass(inputPass), salt);
    }

    public static void main(String[] args) {
        System.out.println(inputPassFormPass("123456"));
        System.out.println(formPassToDBPass(inputPassFormPass("123456"), "1a2b3c4d"));
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
    }
}
