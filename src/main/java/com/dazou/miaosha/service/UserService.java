package com.dazou.miaosha.service;

import com.dazou.miaosha.dao.UserDao;
import com.dazou.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: dazou
 * @Description:
 * @Date: Create in 16:05 2019-04-08
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    //事务标签，保证以下行为在一个事务中，因为插入u2失败，u1也将会回滚，不能插入成功
    //如果没有该标签，将无法保证以下行为在一个事务中，插入u2的失败并不会导致u1的回滚，造成u1插入成功
    public boolean tx() {
        User u1 = new User();
        u1.setId(2);
        u1.setName("dazou");
        userDao.insert(u1);

        User u2 = new User();
        u2.setId(1);
        u2.setName("11111");
        userDao.insert(u2);

        return true;
    }

}
