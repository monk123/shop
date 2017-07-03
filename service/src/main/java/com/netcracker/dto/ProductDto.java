package com.netcracker.dto;

import com.netcracker.pojo.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class ProductDto {

    @Getter @Setter
    private Product product;
    @Getter @Setter
    private int count;

    public ProductDto() {
    }

    public ProductDto(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto)) return false;

        ProductDto that = (ProductDto) o;

        if (count != that.count) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
