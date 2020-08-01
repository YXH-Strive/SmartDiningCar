package com.yxh.controller;

import com.yxh.pojo.User;
import com.yxh.service.UserService;
import com.yxh.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author YXH
 * @date 2020/7/27 - 17:03
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    //验证用户名 密码
    @RequestMapping("checkLogin")
    @ResponseBody
    public Boolean checkLogin(User user) {
        Boolean bool = false;
        bool = userService.selectUserLogin(user);
        System.out.println(user.getPhone() + "...正在验证登录..." + bool);
        return bool;
    }

    //注册账户
    @RequestMapping("checkSign")
    @ResponseBody
    public void checkSign(User user, String initfoods) {
        System.out.println(initfoods);
        System.out.println(1);
        System.out.println(user.toString());
        user.setMoney(0.0);                     //新用户的money=0
        userService.checkUserSign(user);                    //写入数据库
        Tools.sendinitfoods2JW(user.getPhone(), initfoods);//用户id+商品id+5分
        System.out.println(user.getPhone() + "...正在注册");
    }

    //修改用户信息
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public void updateUserInfo(User user) {
        userService.updateUserInfo(user);
        System.out.println(user.getPhone() + "...正在修改用户信息");
    }

    //获取个人信息
    @RequestMapping("initUserInfo")
    @ResponseBody
    public User initUserInfo(String phone) {
        System.out.println(phone + "...正在获取个人信息");
        return userService.selectUser(phone);
    }

    //更新用户的钱包
    @RequestMapping("updateMoney")
    @ResponseBody
    public double updateMoney(String phone, double money) {
        double updateDBMoney = 0.0;
        updateDBMoney = userService.updateDBMoney(phone, money);
        System.out.println("用户：" + phone + "当前的余额为：" + money);
        return updateDBMoney;
    }
}

