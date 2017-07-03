package com.netcracker.service.impl;

import com.netcracker.auth.AuthenticationSecurityService;
import com.netcracker.business.PriceService;
import com.netcracker.dao.OrderDao;
import com.netcracker.dao.ProductDao;
import com.netcracker.dao.UserDao;
import com.netcracker.dto.ProductDto;
import com.netcracker.pojo.Order;
import com.netcracker.pojo.Product;
import com.netcracker.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link OrderService} and extend {@link BaseServiceImpl}
 *
 * @author Ayupov Vadim
 */
@Log
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

   private OrderDao orderDao;
   private ProductDao productDao;
   private UserDao userDao;
   private AuthenticationSecurityService authenticationSecurityService;
   private PriceService priceService;

   @Autowired
   public OrderServiceImpl(OrderDao orderDao,
                           ProductDao productDao,
                           UserDao userDao,
                           AuthenticationSecurityService authenticationSecurityService,
                           PriceService priceService) {

       this.orderDao = orderDao;
       this.productDao = productDao;
       this.userDao = userDao;
       this.authenticationSecurityService = authenticationSecurityService;
       this.priceService = priceService;
   }

    @Override
    @Transactional
    public void addOrder(Order order, List<ProductDto> dto) {
       order.setUser(order.getUser());
       order.setAmount(order.getAmount());
       order.setOrderDate(order.getOrderDate());
       order.setProducts(order.getProducts());
       orderDao.save(order);
   }

    @Override
    public void deleteProductFromBucket(List<ProductDto> products, int index) {
        if (products.get(index).getCount() > 1) {
            int count = products.get(index).getCount() - 1;
            products.get(index).setCount(count);
        } else {
            products.remove(index);
        }
    }
}
