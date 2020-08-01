package com.yxh.service.impl;

import com.yxh.mapper.OrderMapper;
import com.yxh.pojo.CookOrder;
import com.yxh.pojo.Order;
import com.yxh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YXH
 * @date 2020/7/27 - 11:59
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> getOrderByPhone(String phone) {
        return orderMapper.getOrderByPhone(phone);
    }

    @Override
    public void writeGrade(String order_id, int grade) {
        orderMapper.writeGrade(order_id,grade);
    }

    @Override
    public void writeOrder2DB(Order order) {
        orderMapper.writeOrder2DB(order);
    }

    @Override
    public String getFoodId(String order_id) {
        return orderMapper.getFoodId(order_id);
    }

    @Override
    public void writecookOrder2DB(CookOrder cookOrder) {
        orderMapper.writecookOrder2DB(cookOrder);
    }

    public Integer selectTableStatusNum() {
        Integer num;
        num = orderMapper.selectTableStatusNum();
        if(num!=null) {
            return num.intValue();
        }
        return 0;
    }

    public void updateTableStatus() {
        orderMapper.updateTableStatus();
    }
}

