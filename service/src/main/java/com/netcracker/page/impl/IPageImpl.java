package com.netcracker.page.impl;

import com.netcracker.dto.PriceDto;
import com.netcracker.page.IPage;
import com.netcracker.service.ProductService;
import com.netcracker.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link IPage} interface
 *
 * @author Ayupov Vadim
 */

@Log
@Service
public class IPageImpl implements IPage {

    private ProductService productService;
    private UserService userService;

    @Autowired
    public IPageImpl(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public int getSizeProduct(int total) {
        int productsSize = productService.getAllEntities().size();

        return (productsSize % total == 0) ? (productsSize/total) : (productsSize/total + 1);
    }

    @Override
    public int getSizeProductWithString(String name, int total) {
        int productsSize = productService.getProductByCategoryNameSize(name).size();

        return (productsSize %  total == 0) ? (productsSize/total) : (productsSize/total + 1);
    }

    @Override
    public int getPage(int page, int total) {
        if (page != 1) {
            page = (page - 1) * total + 1;
        }

        return page;
    }

    @Override
    public int getSizeUser(int total) {
        int size = userService.getAllEntities().size();

        return (size % total == 0) ? (size/total) : (size/total) + 1;
    }

    @Override
    public int getSizeFindProduct(String name, int total) {
        int size = productService.findProductSize(name).size();

        return (size % total == 0) ? (size/total) : (size/total) + 1;
    }

    @Override
    public int getSizeProductByPrice(PriceDto dto, int total) {
        int size = productService.getProductByPriceSize(dto).size();

        return (size % total == 0) ? (size/total) : (size/total) + 1;

    }
}
