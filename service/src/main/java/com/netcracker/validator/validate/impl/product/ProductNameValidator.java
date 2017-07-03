package com.netcracker.validator.validate.impl.product;

import com.netcracker.validator.validate.ValidatorService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of {@link ValidatorService} interface
 *
 * @author Ayupov Vadim
 */

@Component
@Log
public class ProductNameValidator implements ValidatorService {

    private Pattern pattern;

    /**
     * Набор из букв и цифр (латиница + кириллица)
     */
    private static final String PRODUCT_NAME_PATTERN = "^.*[а-яА-яёЁa-zA-Z_0-9]$";

    public ProductNameValidator() {
        this.pattern = Pattern.compile(PRODUCT_NAME_PATTERN);
    }

    @Override
    public boolean valid(final String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
