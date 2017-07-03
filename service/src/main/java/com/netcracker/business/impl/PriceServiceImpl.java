package com.netcracker.business.impl;

import com.netcracker.business.PriceService;
import com.netcracker.dao.ProductDao;
import com.netcracker.dto.ProductDto;
import com.netcracker.pojo.Product;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * Implementation of {@link PriceService} interface
 *
 * @author Ayupov Vadim
 */

@Log
@Service
public class PriceServiceImpl implements PriceService {

    private ProductDao productDao;

    @Autowired
    public PriceServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public double totalPriceProduct(List<ProductDto> products) {
        double amount = 0.0;

        for (ProductDto product : products) {
            amount+=product.getProduct().getPrice() * product.getCount();
            /*int count = product.getProduct().getCount() - 1;
            product.getProduct().setCount(count);
            productDao.save(product.getProduct());*/
        }

        log.info("Amount: " + amount);

        return amount;
    }
}
