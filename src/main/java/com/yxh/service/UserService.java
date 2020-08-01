package com.yxh.service;

import com.yxh.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author YXH
 * @date 2020/7/27 - 11:58
 */
@Service
public interface UserService {
    //验证用户名和密码
    public boolean selectUserLogin(User user);

    //注册用户
    public void checkUserSign(User user);

    //修改用户信息
    public void updateUserInfo(User user);

    //根据手机号查询用户信息
    public User selectUser(String phone);

    //更新钱包
    public double updateDBMoney(String phone,double money);
}
