package com.dazou.miaosha.validator;

import com.dazou.miaosha.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 17:32 2019-04-08
 */
public class isMobileValidator implements ConstraintValidator<isMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(isMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else { //允许手机号为空
            if (StringUtils.isEmpty(value))
                return true;
            else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
