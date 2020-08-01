package com.yxh.service.impl;

import com.yxh.mapper.UserMapper;
import com.yxh.pojo.User;
import com.yxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YXH
 * @date 2020/7/27 - 12:00
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean selectUserLogin(User user) {
        return userMapper.selectUserLogin(user);
    }

    @Override
    public void checkUserSign(User user) {
        userMapper.checkUserSign(user);
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
    }

    @Override
    public User selectUser(String phone) {
        return userMapper.selectUser(phone);
    }

    @Override
    public double updateDBMoney(String phone, double money) {
        return userMapper.updateDBMoney(phone, money);
    }
}
