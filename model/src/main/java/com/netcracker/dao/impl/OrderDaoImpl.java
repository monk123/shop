package com.netcracker.dao.impl;

import com.netcracker.dao.OrderDao;
import com.netcracker.pojo.Order;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

/**
 * Implementation of {@link OrderDao} interface
 * Extends of {@link BaseDaoImpl} class
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Log
@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao {
}
