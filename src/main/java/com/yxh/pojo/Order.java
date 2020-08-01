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
public class Order {
    private String order_id;    //订单id
    private String phone;       //下单人的手机号
    private String food_name;   //菜名
    private int count;          //购买数量
    private String icon;        //食品图片
    private String time;        //购买时间
    private int grade;          //消费者评分
    private double price;       //价格
    private String table_id;    //桌号
}
