package com.netcracker.service.impl;

import com.netcracker.dao.CategoryDao;
import com.netcracker.dao.ProductDao;
import com.netcracker.dto.PriceDto;
import com.netcracker.pojo.Category;
import com.netcracker.pojo.Product;
import com.netcracker.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

/**
 * Implementation of {@link ProductService} and extend {@link BaseServiceImpl}
 *
 * @author Ayupov Vadim
 */
@Log
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

    private ProductDao productDao;
    private CategoryDao categoryDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> paginationProduct(int firstValue, int maxValue) {
        return productDao.paginationProduct(firstValue, maxValue);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductByCategoryName(String name, int firstValue, int maxValue) {
        return productDao.getProductByCategoryName(name, firstValue, maxValue);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductByCategoryNameSize(String name) {
        return productDao.getProductByCategoryNameSize(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductByPrice(PriceDto dto, int firstValue, int secondValue) {
        return productDao.getProductByPrice(dto.getPriceFrom(), dto.getPriceTo(), firstValue, secondValue);
    }

    @Override
    @Transactional
    public List<Product> getProductByPriceAndCategory(String category,
                                                      PriceDto dto,
                                                      int startValue,
                                                      int secondValue) {

        Category category1 = categoryDao.findCategoryByCategoryName(category);

        return productDao.getProductByPriceAndCategory(
                category1.getCategoryName(),
                dto.getPriceFrom(),
                dto.getPriceTo(),
                startValue,
                secondValue
        );
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        if(product.getCategory().getId() == null) {
            categoryDao.save(product.getCategory());
        } else {
            categoryDao.update(product.getCategory());
        }

        productDao.save(product);
    }

    @Override
    @Transactional
    public void editProduct(Product product) {
        if (product.getCategory().getId() != null) {
            categoryDao.update(product.getCategory());
        } else {
            categoryDao.save(product.getCategory());
        }

        productDao.update(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProduct(String name, int page, int total) {
        return productDao.findProduct(name, page, total);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductSize(String name) {
        return productDao.findProductSize(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductByPriceSize(PriceDto dto) {
        return productDao.getProductByPriceSize(dto.getPriceFrom(), dto.getPriceTo());
    }

    @Override
    public List<Product> getProductByOrderId(Long id) {
        return productDao.getProductByOrderId(id);
    }
}
