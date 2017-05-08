package com.netcracker.service;

import com.netcracker.pojo.Category;

import java.util.List;

public interface CategoryService extends BaseService<Category, Long> {

    Category findCategoryByCategoryName(String categoryName);

    List<String> getCategoryByUniqueName();
}
