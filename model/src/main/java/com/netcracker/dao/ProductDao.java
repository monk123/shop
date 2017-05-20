package com.netcracker.dao;

import com.netcracker.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductDao extends BaseDao<Product, Long> {

    List<Product> paginationProduct(int firstValue, int maxValue);

    Product findProduct(String firstName);

    List<Product> desc(int first, int second);

    List<Product> asc(int first, int second);

    List<Product> between(int first, int second, int startPrice, int finishPrice);
}
