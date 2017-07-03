package com.netcracker.pojo;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean object of representation a Category
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Log
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 6139255037128362425L;

    @Column(name = "CATEGORY_NAME")
    @Getter @Setter
    private String categoryName;

    @Column(name = "DESCRIPTION")
    @Getter @Setter
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Getter @Setter
    private Set<Product> products = new HashSet<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        return categoryName != null ? categoryName.equals(category.categoryName) : category.categoryName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + categoryName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

    public void add(Product product) {
        products.add(product);
        product.setCategory(this);
    }
}
