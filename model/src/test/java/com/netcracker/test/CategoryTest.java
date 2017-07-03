package com.netcracker.test;

import com.netcracker.dao.CategoryDao;
import com.netcracker.pojo.Category;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/model-spring-test.xml")
@Rollback
@Transactional
public class CategoryTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void testSaveCategory() {
       int size = categoryDao.getAllEntities().size();
       Category category = new Category();
       category.setCategoryName("category save");

       categoryDao.save(category);

       assertTrue(size < categoryDao.getAllEntities().size());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setCategoryName("category update");

        categoryDao.save(category);

        category.setCategoryName("category update test");

        Category categoryTest = categoryDao.getEntityById(category.getId());
        categoryDao.update(category);

        assertEquals("category update test", categoryTest.getCategoryName());
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setCategoryName("category findCategoryByCategoryName");

        categoryDao.save(category);

        Category categoryTest = categoryDao.getEntityById(category.getId());

        assertEquals(category, categoryTest);
    }

    @Test
    public void testGetAllCategories() {
      //  assertEquals(0, categoryDao.getAllEntities().size());

        List<Category> categories = Arrays.asList(
                new Category("category 1"),
                new Category("category 2"),
                new Category("category 3"),
                new Category("category 3"),
                new Category("category 1"),
                new Category("category 1"),
                new Category("category 1"),
                new Category("category 1")
        );

        categories.forEach(category -> categoryDao.save(category));

        List<Category> categoriesTest = categoryDao.getAllEntities();

        assertEquals(8, categoriesTest.size());

        List<String> categories1 = categoryDao.getCategoryByUniqueName();

        categories1.forEach(category1 -> log.info("Category unique list:" + category1));

        categoriesTest.forEach(category -> assertTrue(categories.contains(category)));
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setCategoryName("category delete");

        categoryDao.save(category);

        assertEquals(category, categoryDao.getEntityById(category.getId()));

        categoryDao.delete(category.getId());

        assertNull(categoryDao.getEntityById(category.getId()));
    }

    @Test
    public void testFindProduct() {
        Category category = new Category();
        category.setCategoryName("Keyboards");

        categoryDao.save(category);

        Category categoryTest = categoryDao.findCategoryByCategoryName("Keyboards");
        log.info("Category: " + categoryTest.getCategoryName());
        assertEquals(category, categoryTest);
    }
}
