package com.yxh.mapper;

import com.yxh.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

/**
 * @author YXH
 * @date 2020/7/27 - 12:13
 */
@Mapper
public interface UserMapper {
    //验证用户名和密码
    public boolean selectUserLogin(User user);

    //注册用户
    public void checkUserSign(User user);

    //修改用户信息
    public void updateUserInfo(User user);

    //根据手机号查询用户信息
    public User selectUser(String phone);

    //更新钱包
    public double updateDBMoney(@Param("phone") String phone, @Param("mooney") double money);
}
