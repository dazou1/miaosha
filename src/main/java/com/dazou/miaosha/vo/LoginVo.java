package com.dazou.miaosha.vo;

import com.dazou.miaosha.validator.isMobile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 16:56 2019-04-08
 */

@Getter
@Setter
@ToString
public class LoginVo {

    @NotNull
    @isMobile //自定义注解
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
