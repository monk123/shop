package com.netcracker.pojo;


import lombok.*;
import lombok.extern.java.Log;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean object that representation a Product
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Entity
@Log
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {

    private static final long serialVersionUID = -4916412962878126741L;

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

    @Column(name = "COUNT")
    @Getter @Setter
    private Integer count;

    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID")
    @Getter @Setter
    private Category category;

    @ManyToMany(mappedBy = "products")
    @Getter @Setter
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Getter @Setter
    private Set<Comment> comments;

    public Product(String name, String description, Double price, String photo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (photo != null ? !photo.equals(product.photo) : product.photo != null) return false;
        return count != null ? count.equals(product.count) : product.count == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", photo='" + photo + '\'' +
                '}';
    }
}
