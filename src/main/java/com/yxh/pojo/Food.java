package com.yxh.pojo;

import lombok.*;

import java.util.HashMap;

/**
 * @author YXH
 * @date 2020/7/27 - 11:38
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private int food_id;
    private String name;
    private int sales;
    private double grade;
    private double price;
    private int type;
    private String type_name;
    private int count;
    private String icon;
    public HashMap<String,Object> foods = new HashMap<>();
}
