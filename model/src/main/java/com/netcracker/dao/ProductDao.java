package com.netcracker.dao;

import com.netcracker.pojo.Category;
import com.netcracker.pojo.Product;

import java.util.List;
import java.util.stream.Stream;

public interface ProductDao extends BaseDao<Product, Long> {

    List<Product> paginationProduct(int firstValue, int maxValue);

    List<Product> getProductByCategoryName(String name, int firstValue, int maxValue);

    List<Product> getProductByCategoryNameSize(String name);

    List<Product> getProductByPrice(double priceFrom, double priceTo,
                                    int firstValue, int secondValue);

    List<Product> getProductByPriceAndCategory(String category, double priceFrom, double priceTo,
                                               int firstValue, int secondValue);

    List<Product> findProduct(String name, int page, int total);

    List<Product> getProductByPriceSize(double priceFrom, double priceTo);

    List<Product> findProductSize(String name);

    List<Product> getProductByOrderId(Long id);
}
