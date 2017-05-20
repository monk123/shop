package com.netcracker.test;

import com.netcracker.dao.OrderDao;
import com.netcracker.pojo.Order;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/model-spring-test.xml")
@Rollback
@Transactional
public class OrderTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testOrderSave() {
        int size = orderDao.getAllEntities().size();

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setAmount(120.59);
        order.setOrderNumber(1);

        orderDao.save(order);

        assertTrue(size < orderDao.getAllEntities().size());
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setAmount(120.59);
        order.setOrderNumber(1);

        orderDao.save(order);

        order.setAmount(100.29);

        Order orderTest = orderDao.getEntityById(order.getId());
        orderDao.update(order);

        assertNotEquals(110.29, orderTest.getAmount());
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setAmount(120.59);
        order.setOrderNumber(1);

        orderDao.save(order);
        Order orderTest = orderDao.getEntityById(order.getId());

        assertEquals(order, orderTest);
    }

    @Test
    public void testGetAllOrders() {
        assertEquals(0, orderDao.getAllEntities().size());

        List<Order> orders = Arrays.asList(
                new Order(new Date(),120.99, 1),
                new Order(new Date(), 120.99, 2),
                new Order(new Date(), 120.99, 3),
                new Order(new Date(), 120.99, 4)
        );

        orders.forEach(order -> orderDao.save(order));

        List<Order> ordersTest = orderDao.getAllEntities();
        assertEquals(4, ordersTest.size());

        ordersTest.forEach(order -> assertTrue(orders.contains(order)));
    }

    @Test
    public void testDeleteOrder() {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setAmount(120.59);
        order.setOrderNumber(1);

        orderDao.save(order);

        assertEquals(order, orderDao.getEntityById(order.getId()));

        orderDao.delete(order.getId());

        assertNull(orderDao.getEntityById(order.getId()));
    }





}
