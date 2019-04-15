package com.dazou.miaosha.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 17:28 2019-04-08
 */

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { isMobileValidator.class})//将会使用isMobileValidator定义的规则进行判断
public @interface isMobile {

    boolean required() default true; //不允许手机号为空

    String message() default "手机号码格式出错";//验证不通过时的输出信息

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
