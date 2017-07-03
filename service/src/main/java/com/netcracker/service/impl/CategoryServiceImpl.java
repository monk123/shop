package com.netcracker.service.impl;

import com.netcracker.dao.CategoryDao;
import com.netcracker.pojo.Category;
import com.netcracker.service.CategoryService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link CategoryService} and extend {@link BaseServiceImpl}
 *
 * @author Ayupov Vadim
 */
@Log
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Long> implements CategoryService {

    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Category findCategoryByCategoryName(String categoryName) {
        return categoryDao.findCategoryByCategoryName(categoryName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getCategoryByUniqueName() {
        return categoryDao.getCategoryByUniqueName();
    }
}
