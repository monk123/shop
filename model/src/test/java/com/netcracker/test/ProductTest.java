package com.netcracker.test;

import com.netcracker.dao.CategoryDao;
import com.netcracker.dao.ProductDao;
import com.netcracker.pojo.Category;
import com.netcracker.pojo.Product;
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
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/model-spring-test.xml")
@Rollback
@Transactional
public class ProductTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void testSaveProduct() {
        int size = productDao.getAllEntities().size();

        Product product = new Product();
        product.setName("Keyboards test save");
        product.setDescription("Keyboards description test save");
        product.setPrice(24.99);
        product.setPhoto("keyboards.jpg");

        productDao.save(product);

        assertTrue(size < productDao.getAllEntities().size());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setName("Keyboards test update");
        product.setDescription("Keyboards description test update");
        product.setPrice(24.99);
        product.setPhoto("keyboards.jpg");

        productDao.save(product);

        product.setName("keyboards test update after save");

        Product productTest = productDao.getEntityById(product.getId());
        productDao.update(product);

        assertEquals("keyboards test update after save", productTest.getName());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setName("Keyboards test findCategoryByCategoryName");
        product.setDescription("Keyboards description test findCategoryByCategoryName");
        product.setPrice(24.99);
        product.setPhoto("keyboards.jpg");

        productDao.save(product);

        Product productTest = productDao.getEntityById(product.getId());
        assertEquals(product, productTest);
    }

    @Test
    public void testGetAllProducts() {
        assertEquals(0, productDao.getAllEntities().size());

        List<Product> products = Arrays.asList(
                new Product("Keyboards 1", "desc 1", 24.99, "key.jpg"),
                new Product("Keyboards 2", "desc 2", 24.99, "key.jpg"),
                new Product("Keyboards 3", "desc 3", 24.99, "key.jpg"),
                new Product("Keyboards 4", "desc 4", 24.99, "key.jpg")
        );

        products.forEach(product -> productDao.save(product));

        List<Product> productsTest = productDao.getAllEntities();
        assertEquals(4, productsTest.size());

        productsTest.forEach(product -> assertTrue(products.contains(product)));
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setName("Keyboards test delete");
        product.setDescription("Keyboards description test delete");
        product.setPrice(24.99);
        product.setPhoto("keyboards.jpg");

        productDao.save(product);

        assertEquals(product, productDao.getEntityById(product.getId()));

        productDao.delete(product.getId());

        assertNull(productDao.getEntityById(product.getId()));
    }

    @Test
    public void testPaginationProduct() {
        List<Product> products = Arrays.asList(
                new Product("Keyboards 1", "desc 1", 24.99, "key.jpg"),
                new Product("Keyboards 2", "desc 2", 24.99, "key.jpg"),
                new Product("Keyboards 3", "desc 3", 24.99, "key.jpg"),
                new Product("Keyboards 4", "desc 4", 24.99, "key.jpg"),
                new Product("Keyboards 5", "desc 5", 24.99, "key.jpg"),
                new Product("Keyboards 6", "desc 6", 24.99, "key.jpg"),
                new Product("Keyboards 7", "desc 7", 24.99, "key.jpg")
        );

        products.forEach(product -> productDao.save(product));

        List<Product> productsTest = productDao.paginationProduct(1, 5);
       // productsTest.forEach(product -> log.info("Products: " + product));
        productsTest.forEach(product -> log.info("Products list:" + product));

        assertEquals(5, productsTest.size());
    }

    @Test
    public void testCountProduct() {
        List<Product> products = Arrays.asList(
                new Product("Keyboards 1", "desc 1", 24.99, "key.jpg"),
                new Product("Keyboards 2", "desc 2", 24.99, "key.jpg"),
                new Product("Keyboards 3", "desc 3", 24.99, "key.jpg"),
                new Product("Keyboards 4", "desc 4", 24.99, "key.jpg"),
                new Product("Keyboards 5", "desc 5", 24.99, "key.jpg"),
                new Product("Keyboards 6", "desc 6", 24.99, "key.jpg"),
                new Product("Keyboards 7", "desc 7", 24.99, "key.jpg")
        );

        products.forEach(product -> productDao.save(product));

        List<Product> productsTest = productDao.getAllEntities();

        int size = products.size();

        assertEquals(size, productsTest.size());
    }

    @Test
    public void testGetProductByCategoryName() {
        Category category = new Category();
        category.setCategoryName("keyboards");

        Category category1 = new Category();
        category1.setCategoryName("tv");

        List<Product> products = Arrays.asList(
                new Product("Keyboards 1", "desc 1", 24.99, "key.jpg"),
                new Product("Keyboards 1", "desc 1", 24.99, "key.jpg"),
                new Product("Keyboards 1", "desc 1", 24.99, "key.jpg"),
                new Product("Keyboards 1", "desc 1", 24.99, "key.jpg"),
                new Product("Keyboards 1", "desc 1", 24.99, "key.jpg")
        );

        products.forEach(product -> productDao.save(product));

        List<Product> productsTest = productDao.getProductByCategoryName("tv", 0, 5);

        productsTest.forEach(product -> log.info("Products list:" + product));
    }

}
