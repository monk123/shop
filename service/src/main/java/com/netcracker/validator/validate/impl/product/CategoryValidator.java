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
public class CategoryValidator implements ValidatorService {

    private static final String PATTERN_CATEGORY = ".*[а-яА-ЯёЁa-zA-Z]+$";

    private Pattern pattern;

    public CategoryValidator() {
        this.pattern = Pattern.compile(PATTERN_CATEGORY);
    }

    @Override
    public boolean valid(String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
