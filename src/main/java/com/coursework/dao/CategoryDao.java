package com.coursework.dao;

import com.coursework.entity.Category;
import java.util.ArrayList;

public interface CategoryDao {
    boolean insertCategory(Category category);
    ArrayList<Category> fetchAllCategories();
    Category findCategoryById(int id);
    boolean updateCategory(Category category);
    boolean deleteCategory(int id);
}