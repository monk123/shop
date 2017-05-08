package com.netcracker.service.impl;

import com.netcracker.dao.ProductDao;
import com.netcracker.pojo.Product;
import com.netcracker.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public List<Product> paginationProduct(int firstValue, int maxValue) {
        return productDao.paginationProduct(firstValue, maxValue);
    }

    @Override
    @Transactional
    public Product findProduct(String name) {
        return productDao.findProduct(name);
    }

    @Override
    public List<Product> getProductByCategoryName(String name, int firstValue, int maxValue) {
        return productDao.getProductByCategoryName(name, firstValue, maxValue);
    }
}
