package com.dazou.miaosha.exception;

import com.dazou.miaosha.result.CodeMsg;
import com.dazou.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 18:35 2019-04-08
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class) //拦截所有的异常
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {

        e.printStackTrace();

        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getCm());
        }
        else if (e instanceof BindException) {
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);//取出第一个
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
