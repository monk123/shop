package com.netcracker.validator.validate.impl.user;

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
public class PhoneValidator implements ValidatorService {

    private Pattern pattern;

    /**
     * регулярное выражение позволяет записать мобильный телефон в виде +375(29)747-41-27
     */
    private static final String PHONE_PATTERN = "[\\+]\\d{3}[\\(]\\d{2}[\\)]\\d{3}[\\-]\\d{2}[\\-]\\d{2}";

    public PhoneValidator() {
        this.pattern = Pattern.compile(PHONE_PATTERN);
    }

    @Override
    public boolean valid(final String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
