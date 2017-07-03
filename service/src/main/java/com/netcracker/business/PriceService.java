package com.netcracker.business;

import com.netcracker.dto.ProductDto;

import java.util.List;

public interface PriceService {

    double totalPriceProduct(List<ProductDto> products);
}
