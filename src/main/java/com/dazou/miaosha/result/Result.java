package com.dazou.miaosha.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 10:22 2019-04-08
 */

@Getter
@Setter
public class Result <T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.data = data;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }
    //成功时调用
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }
    //失败时调用
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }
}
