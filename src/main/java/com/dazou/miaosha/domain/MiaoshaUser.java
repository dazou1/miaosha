package com.dazou.miaosha.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 17:12 2019-04-08
 */

@Setter
@Getter
public class MiaoshaUser {

    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;

}
