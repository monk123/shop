package com.netcracker.validator.valid.impl.product;

import com.netcracker.validator.valid.ValidatorService;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProductNameValidator implements ValidatorService {

    private Pattern pattern;

    /**
     * Набор из букв и цифр (латиница + кириллица)
     */
    private static final String PRODUCT_NAME_PATTERN = "[а-яА-ЯёЁa-zA-Z0-9]+$";

    public ProductNameValidator() {
        this.pattern = Pattern.compile(PRODUCT_NAME_PATTERN);
    }

    @Override
    public boolean valid(final String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
