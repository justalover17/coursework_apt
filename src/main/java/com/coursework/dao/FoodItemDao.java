package com.coursework.dao;

import com.coursework.entity.FoodItem;
import java.util.ArrayList;

public interface FoodItemDao {
    boolean insertFood(FoodItem food);
    ArrayList<FoodItem> fetchAllFood();
    FoodItem findFoodById(int id);
    boolean updateFood(FoodItem food);
    boolean deleteFood(int id);
}