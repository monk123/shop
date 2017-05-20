package com.netcracker.model;

import com.netcracker.pojo.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class ProductInfo {

    @Getter @Setter
    private Product product = new Product();
    @Getter @Setter
    private int count;

    public ProductInfo() {
    }

    public ProductInfo(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInfo)) return false;

        ProductInfo that = (ProductInfo) o;

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
        return "ProductInfo{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
