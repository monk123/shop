package com.netcracker.validator.priceFormValidator;

import com.netcracker.dto.PriceDto;
import com.netcracker.validator.validate.impl.product.PriceValidator;
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
public class PriceFormValidatorImpl implements Validator {

    private PriceValidator priceValidator;

    @Autowired
    public PriceFormValidatorImpl(PriceValidator priceValidator) {
        this.priceValidator = priceValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PriceDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PriceDto dto = (PriceDto) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priceFrom", "price.form.validator");
        if (dto.getPriceFrom() < 0) {
            errors.rejectValue("priceFrom", "price.form.validator");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "priceTo", "price.form.validator");
        if (dto.getPriceTo() < 0) {
            errors.rejectValue("priceTo", "price.form.validator");
        }
    }
}
