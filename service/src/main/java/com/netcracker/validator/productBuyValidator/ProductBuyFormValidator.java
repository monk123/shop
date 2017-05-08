package com.netcracker.validator.productBuyValidator;

import com.netcracker.pojo.Product;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component @Log
public class ProductBuyFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Product product = (Product) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "count","NotEmpty.product.count");
        if (product.getCount() < 0 || product.getCount() == null) {
            errors.rejectValue("count", "NotEmpty.product.count");
        }
    }
}
