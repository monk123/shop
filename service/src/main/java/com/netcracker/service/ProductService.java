package com.netcracker.service;

import com.netcracker.model.ProductInfo;
import com.netcracker.pojo.Category;
import com.netcracker.pojo.Product;

import java.util.List;

public interface ProductService extends BaseService<Product, Long> {

    List<Product> paginationProduct(int firstValue, int maxValue);

    Product findProduct(String name);

    List<Product> getProductByCategoryName(String name, int firstValue, int maxValue);

    List<Category> getCategories();

    List<ProductInfo> getProductDTO();

    void addProductDTO(ProductInfo productInfo);

    void deleteProductDTO(ProductInfo productInfo);
}
