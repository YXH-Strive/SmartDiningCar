package com.yxh.controller;

import com.yxh.pojo.CookOrder;
import com.yxh.pojo.Food;
import com.yxh.pojo.SignFood;
import com.yxh.service.FoodService;
import com.yxh.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author YXH
 * @date 2020/7/27 - 17:03
 */
@Controller
public class FoodController {
    @Autowired
    private FoodService foodService;

    private int[] count;
    private List<Food> foods;
    private HashMap<String, Object> hashMap;

    //生成菜单
    @RequestMapping("initMenu")
    @ResponseBody
    public List<HashMap<String, Object>> initMenu(String phone) {
        List<HashMap<String, Object>> menu = new ArrayList<HashMap<String, Object>>();
        count = new int[4];
        int n = 1;
        if (phone != "") {
            //将猜你喜欢的菜添加
            String likeFoodsIds = Tools.getLikeFoodsId(phone);//根据手机号获取菜的id
            System.out.println(phone + "...的猜你喜欢菜是：" + likeFoodsIds);
            if (likeFoodsIds != "" && likeFoodsIds != null) {
                //新建菜单组
                menu.add(new HashMap<String, Object>());
                //根据菜的id，查询菜的全部信息
                foods = foodService.getLikeFoods(likeFoodsIds);
                for (Food food : foods) {
                    food.setType_name("猜你喜欢");
                    getMenu(menu, food, 1);//汇总到大菜单中
                }
                n = 0;
            }
        }
        for (int i = 0; i < 3; i++) {
            menu.add(new HashMap<String, Object>());
        }
        foods = foodService.initMenu();//从数据库中获取
        for (Food food : foods) {
            getMenu(menu, food, food.getType() - n);
        }
        System.out.println(phone + "正在获取菜单...");
        return menu;
    }

    private void getMenu(List<HashMap<String, Object>> menu, Food food, int type) {

        HashMap<String, Object> eachFood = menu.get(type - 1);
        if (eachFood.get("name") == null) {
            eachFood.put("name", food.getType_name());
        }
        if (eachFood.get("type") == null) {
            eachFood.put("type", type);
        }

        if (eachFood.get("foods") == null) {
            eachFood.put("foods", new ArrayList<HashMap<String, Object>>());
        }


        @SuppressWarnings("unchecked")
        ArrayList<HashMap<String, Object>> foods = (ArrayList<HashMap<String, Object>>) eachFood.get("foods");
        hashMap = new HashMap<String, Object>();
        hashMap.put("name", food.getName());
        hashMap.put("price", food.getPrice());
        hashMap.put("sales", food.getSales());
        hashMap.put("Count", 0);
        hashMap.put("grade", food.getGrade());
        hashMap.put("icon", food.getIcon());
        hashMap.put("type", food.getType());
        hashMap.put("index", count[type - 1]++);

        foods.add(hashMap);

    }


    //注册时随机产生6个菜让用户选择
    @RequestMapping("signingShowFoods")
    @ResponseBody
    public List<SignFood> signingShowFoods(int num) {
        List<SignFood> menu = new ArrayList<SignFood>();
        menu = foodService.initSignFoods(num);
        System.out.println("注册界面的随机菜单正在生成..." + menu.size());
        return menu;
    }

    @RequestMapping("GetDataTHI")
    @ResponseBody
    public CookOrder GetDataTHI(String phone) {

        CookOrder c = new CookOrder();
        c.setFoods("11");
        c.setOk(0);
        c.setTable_id(1);
        c.setTime("20190808");
        System.out.println("1");
        return c;
    }


}
