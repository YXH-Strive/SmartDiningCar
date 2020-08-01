package com.yxh.service;

import com.yxh.pojo.Food;
import com.yxh.pojo.SignFood;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YXH
 * @date 2020/7/27 - 11:58
 */
@Service
public interface FoodService {
    /**初始化菜单**/
    public List<Food> initMenu();

    /**初始化注册界面的菜单 **/
    public List<SignFood> initSignFoods(int num);

    /**查询推荐的菜**/
    public List<Food> getLikeFoods(String likeFoodsIds);

}
