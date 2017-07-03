package com.netcracker.service;

import com.netcracker.dto.ProductDto;
import com.netcracker.pojo.Order;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    void addOrder(Order order, List<ProductDto> dto);

    void deleteProductFromBucket(List<ProductDto> products, int index);

}
