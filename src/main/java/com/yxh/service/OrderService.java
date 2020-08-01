package com.yxh.service;

import com.yxh.pojo.CookOrder;
import com.yxh.pojo.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YXH
 * @date 2020/7/27 - 11:58
 */
@Service
public interface OrderService {
    /**根据手机号获取此人全部订单信息**/
    public List<Order> getOrderByPhone(String phone);

    /**打分后，数据写入数据库**/
    public void writeGrade(String order_id, int grade);

    /**新的订单信息，写入数据库**/
    public void writeOrder2DB(Order order);

    /**根据订单id，获取商品id**/
    public String getFoodId(String order_id);

    /**订单概况写入后厨表中**/
    public void writecookOrder2DB(CookOrder cookOrder);
}

