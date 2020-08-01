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
public class SignFood {
    //注册界面的食品
    private int food_id;
    private String icon;
    private String name;
}
