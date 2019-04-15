package com.dazou.miaosha.exception;

import com.dazou.miaosha.result.CodeMsg;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 18:54 2019-04-08
 */
public class GlobalException extends RuntimeException {

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCm() {
        return codeMsg;
    }
}
