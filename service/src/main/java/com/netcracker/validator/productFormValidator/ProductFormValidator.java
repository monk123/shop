package com.netcracker.validator.productFormValidator;

import com.netcracker.pojo.Product;
import com.netcracker.service.ProductService;
import com.netcracker.validator.valid.ValidatorService;
import com.netcracker.validator.valid.impl.product.ProductNameValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component @Log
public class ProductFormValidator implements Validator {

    private ProductNameValidator productNameValidator;

    @Autowired
    public void setProductNameValidator(ProductNameValidator productNameValidator) {
        this.productNameValidator = productNameValidator;
    }

    /**
     * метод supports(Class<?> clazz) регистрирует какие классы должны поддерживать данную валидацию
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    /**
     * метод validate(Object o, Errors errors) осуществляет валидацию, тут мы описываем правила валидации,
     * а точней в каком случае данные будут считаться не валидными
     *
     * @param obj
     * @param errors
     */
    @Override
    public void validate(Object obj, Errors errors) {
        Product product = (Product) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
        if (product.getName() == null) {
            errors.rejectValue("name", "NotEmpty.productName.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.productForm.description");
        if (product.getDescription() == null) {
            errors.rejectValue("description", "NotEmpty.productForm.description");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
        if (product.getPrice() == null || product.getPrice() < 0) {
            errors.rejectValue("price", "NotEmpty.productForm.price");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "NotEmpty.productForm.photo");
        if (product.getPhoto() == null) {
            errors.rejectValue("photo", "NotEmpty.productForm.photo");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty.productForm.category");
        if (product.getCategory().getCategoryName() == null) {
            errors.rejectValue("category", "NotEmpty.productForm.category");
        }
    }
}
