package com.netcracker.dao;

import com.netcracker.pojo.Category;

import java.util.List;

public interface CategoryDao extends BaseDao<Category, Long> {

    Category findCategoryByCategoryName(String categoryName);

    List<String> getCategoryByUniqueName();

}
