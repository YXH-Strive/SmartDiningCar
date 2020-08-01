package com.yxh.pojo;

import lombok.*;

/**
 * @author YXH
 * @date 2020/7/27 - 11:39
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String phone;       //电话
    private String sex;         //性别
    private String age;         //出生日期
    private String stature;     //体重
    private String weight;      //身高
    private String password;    //密码
    private Double money;       //余额
}
