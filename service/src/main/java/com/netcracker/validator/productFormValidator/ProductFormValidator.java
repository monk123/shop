package com.netcracker.validator.productFormValidator;

import com.netcracker.pojo.Product;
import com.netcracker.validator.validate.impl.product.CategoryValidator;
import com.netcracker.validator.validate.impl.product.PriceValidator;
import com.netcracker.validator.validate.impl.product.ProductNameValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Implementation of {@link Validator} interface
 *
 * @author Ayupov Vadim
 */

@Log
@Component
public class ProductFormValidator implements Validator {

    private ProductNameValidator productNameValidator;
    private PriceValidator priceValidator;
    private CategoryValidator categoryValidator;

    @Autowired
    public ProductFormValidator(ProductNameValidator productNameValidator,
                                PriceValidator priceValidator,
                                CategoryValidator categoryValidator) {

        this.productNameValidator = productNameValidator;
        this.priceValidator = priceValidator;
        this.categoryValidator = categoryValidator;
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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (!productNameValidator.valid(product.getName())) {
            errors.rejectValue("name", "Name.productForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Required");
        if (product.getDescription() == null) {
            errors.rejectValue("description", "NotEmpty.productForm.description");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Required");
        if (!priceValidator.valid(String.valueOf(product.getPrice()))) {
            errors.rejectValue("price", "Price.productForm.error");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            errors.rejectValue("price", "NotEmpty.productForm.price");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "Required");
        if (!categoryValidator.valid(product.getCategory().getCategoryName())) {
            errors.rejectValue("category", "Category.productForm.error");
        }
        if (product.getCategory().getCategoryName() == null) {
            errors.rejectValue("category", "NotEmpty.productForm.category");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "Required");
        if (product.getPhoto() == null) {
            errors.rejectValue("photo", "NotEmpty.productForm.photo");
        }
    }
}
