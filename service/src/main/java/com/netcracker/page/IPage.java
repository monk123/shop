package com.netcracker.page;


import com.netcracker.dto.PriceDto;

public interface IPage {

    int getSizeProduct(int total);

    int getSizeProductWithString(String name, int total);

    int getPage(int page, int total);

    int getSizeUser(int total);

    int getSizeFindProduct(String name, int total);

    int getSizeProductByPrice(PriceDto dto, int total);
}
