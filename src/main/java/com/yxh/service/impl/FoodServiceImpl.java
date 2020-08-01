package com.yxh.service.impl;

import com.yxh.mapper.FoodMapper;
import com.yxh.pojo.Food;
import com.yxh.pojo.SignFood;
import com.yxh.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YXH
 * @date 2020/7/27 - 11:59
 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;

    @Override
    public List<Food> initMenu() {
        return foodMapper.initMenu();
    }

    @Override
    public List<SignFood> initSignFoods(int num) {
        return foodMapper.initSignFoods(num);
    }

    @Override
    public List<Food> getLikeFoods(String likeFoodsIds) {
        return foodMapper.getLikeFoods(likeFoodsIds);
    }
}
