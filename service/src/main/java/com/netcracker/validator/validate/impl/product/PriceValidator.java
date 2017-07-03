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

@Log
@Component
public class PriceValidator implements ValidatorService {

    private Pattern pattern;

    /**
     * Позвлоляет записать выражения вида 1 11 1,02 1.02
     * два знака после запятой
     */
    private static final String PATTERN_PRICE = "[0-9]+([,.][0-9]{1,2})?";

    public PriceValidator() {
        this.pattern = Pattern.compile(PATTERN_PRICE);
    }

    @Override
    public boolean valid(String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
