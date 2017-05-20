package com.netcracker.validator.valid.impl.user;

import com.netcracker.validator.valid.ValidatorService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of {@link ValidatorService} interface
 *
 * @author Ayupov Vadim
 */

@Component @Log
public class LastNameValidator implements ValidatorService {

    private Pattern pattern;

    /**
     * Набор из букв(латиница + кириллца)
     */
    private static final String LASTNAME_PATTER = "[а-яА-ЯёЁa-zA-Z]{2,15}";

    public LastNameValidator() {
        this.pattern = Pattern.compile(LASTNAME_PATTER);
    }

    @Override
    public boolean valid(final String lastName) {
        Matcher matcher = pattern.matcher(lastName);
        return matcher.matches();
    }
}
