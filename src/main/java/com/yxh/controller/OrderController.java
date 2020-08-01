package com.yxh.controller;

import com.google.gson.Gson;
import com.yxh.pojo.CookOrder;
import com.yxh.pojo.Order;
import com.yxh.service.OrderService;
import com.yxh.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author YXH
 * @date 2020/7/27 - 17:03
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    private List<Order> orders;

    //根据手机号查询关于他的全部订单信息，并返回
    @RequestMapping("initOrderInfo")
    @ResponseBody
    public List<Order> initOrderInfo(String phone) {
        System.out.println(phone);
        orders = orderService.getOrderByPhone(phone);
        for (Order order : orders) {
            order.setTime(order.getTime().split("\\.")[0]);
        }
        System.out.println(phone + "..正在获取订单信息");
        return orders;
    }

    //将评分写入数据库
    @RequestMapping("writeGrade")
    @ResponseBody
    public boolean writeGrade(String order_id, int grade, String phone) {
        orderService.writeGrade(order_id, grade);//将评分数据写入数据库
        String food_id = orderService.getFoodId(order_id);//根据订单id查询菜名查询菜id
        Tools.sendinitfoods2JW(phone, food_id, grade);//评分数据发送到数据处理服务器
        System.out.println(order_id + "的评分已经成功更新！");
        return true;
    }

    //把订单信息写入订单表中
    @RequestMapping("newOrder")
    @ResponseBody
    public int newOrder(String phone, String food_name, String count, String price, String icon, String tableId) {

        int l = food_name.split("-").length;//几种
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        SimpleDateFormat sd = new SimpleDateFormat("YYYYMMddHHmmss");
        String date_Long = sdf.format(date);
        String date_Short = sd.format(date);

        String[] foodNames = food_name.split("-");
        String[] counts = count.split("-");
        String[] prices = price.split("-");
        String[] icons = icon.split("-");

        Gson gson = new Gson();
        List<HashMap<String, String>> foodsMap = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < l; i++) {
            HashMap<String, String> name_num = new HashMap<String, String>();
            String order_id = date_Short + i;  //订单id
            String foodName = foodNames[i];        //食品名称
            int num = Integer.parseInt(counts[i]);  //购买数量
            String image = icons[i];                //食品图片
            double money = Double.parseDouble(prices[i]);    //售价

            name_num.put("food_name", foodName);
            name_num.put("count", num + "");
            foodsMap.add(name_num);

            Order o = new Order(order_id, phone, foodName, num, image, date_Long, 0, money, tableId);
            System.out.println(o.getOrder_id() + "正在写入DB。。。");
            orderService.writeOrder2DB(o);
        }
        String foods = gson.toJson(foodsMap);
        CookOrder cookOrder = new CookOrder(Integer.parseInt(tableId), 1, date_Long, foods);
        System.out.println("后厨订单：\n" + cookOrder.toString());
        orderService.writecookOrder2DB(cookOrder);
        return l;
    }


}







