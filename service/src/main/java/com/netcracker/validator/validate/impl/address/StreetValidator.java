package com.netcracker.validator.validate.impl.address;

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
public class StreetValidator implements ValidatorService {

    private Pattern pattern;

    private static final String PATTERN_STREET = ".*[а-яА-ЯёЁa-zA-Z0-9.-]{2,50}";

    public StreetValidator() {
        this.pattern = Pattern.compile(PATTERN_STREET);
    }

    @Override
    public boolean valid(String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
