package com.netcracker.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.java.Log;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean object of representation a Category
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Log @NoArgsConstructor
@Entity @Table(name = "categories")
public class Category extends BaseEntity {
    private static final Long serialVersionUID = 6L;

    @Column(name = "CATEGORY_NAME")
    @Getter @Setter
    private String categoryName;

    @Column(name = "DESCRIPTION")
    @Getter @Setter
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Getter @Setter
    private Set<Product> products = new HashSet<>();

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;

        Category category = (Category) o;

        if (!categoryName.equals(category.categoryName)) return false;
        return description.equals(category.description);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + categoryName.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void add(Product product) {
        products.add(product);
        product.setCategory(this);
    }
}
