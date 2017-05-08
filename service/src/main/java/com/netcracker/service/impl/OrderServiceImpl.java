package com.netcracker.service.impl;

import com.netcracker.dao.OrderDao;
import com.netcracker.pojo.Order;
import com.netcracker.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Log
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
