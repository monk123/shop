package com.netcracker.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean object that representation a Product
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@NoArgsConstructor @Log
@Entity @Table(name = "products")
public class Product extends BaseEntity {
    private static final Long serialVersionUID = 5L;

    @Column(name = "NAME_PRODUCT")
    @Getter @Setter
    private String name;

    @Column(name = "DESCRIPTION")
    @Getter @Setter
    private String description;

    @Column(name = "PRICE")
    @Getter @Setter
    private Double price;

    @Column(name = "PHOTO")
    @Getter @Setter
    private String photo;

    private String code;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    @Getter @Setter
    private Category category;

    @OneToMany(mappedBy = "product")
    @Getter @Setter
    private Set<OrderDetail> orders = new HashSet<>();

    public Product(String name, String description, Double price, String photo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }

    public Product(String name, String description, Double price, String photo, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.category = category;
    }

    public Product(String name, String description, Double price, String photo, String code, Category category, Set<OrderDetail> orders) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.code = code;
        this.category = category;
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;
        if (!description.equals(product.description)) return false;
        if (!price.equals(product.price)) return false;
        return photo.equals(product.photo);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                '}';
    }
}
