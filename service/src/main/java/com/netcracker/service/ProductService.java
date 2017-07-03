package com.netcracker.service;

import com.netcracker.dto.PriceDto;
import com.netcracker.pojo.Product;

import java.util.List;
import java.util.stream.Stream;

public interface ProductService extends BaseService<Product, Long> {

    List<Product> paginationProduct(int firstValue, int maxValue);

    List<Product> getProductByCategoryName(String name, int firstValue, int maxValue);

    List<Product> getProductByCategoryNameSize(String name);

    List<Product> getProductByPrice(PriceDto dto, int firstValue, int secondValue);

    List<Product> getProductByPriceAndCategory(String category, PriceDto dto,
                                               int startValue, int secondValue);

    void addProduct(Product product);

    void editProduct(Product product);

    List<Product> findProduct(String name, int page, int total);

    List<Product> findProductSize(String name);

    List<Product> getProductByPriceSize(PriceDto dto);

    List<Product> getProductByOrderId(Long id);
}
