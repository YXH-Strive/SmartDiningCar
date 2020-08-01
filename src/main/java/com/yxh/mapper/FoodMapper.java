package com.yxh.mapper;

import com.yxh.pojo.Food;
import com.yxh.pojo.SignFood;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YXH
 * @date 2020/7/27 - 12:13
 */
@Mapper
public interface FoodMapper {
    /**初始化菜单**/
    public List<Food> initMenu();

    /**初始化,注册界面de菜单 **/
    public List<SignFood> initSignFoods(int num);

    /**查询推荐的菜**/
    public List<Food> getLikeFoods(String likeFoodsIds);
}
