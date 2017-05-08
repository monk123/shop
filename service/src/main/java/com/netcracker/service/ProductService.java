package com.netcracker.service;

import com.netcracker.pojo.Product;
import lombok.extern.java.Log;

import java.util.List;

public interface ProductService extends BaseService<Product, Long> {

    List<Product> paginationProduct(int firstValue, int maxValue);

    Product findProduct(String name);

    List<Product> getProductByCategoryName(String name, int firstValue, int maxValue);
}
