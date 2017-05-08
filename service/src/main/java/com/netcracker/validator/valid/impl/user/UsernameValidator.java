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
public class UsernameValidator implements ValidatorService {

    private Pattern pattern;

    /**
     * с ограничением 3-32 символов, которыми могут быть буквы и цифры, первый символ обязательно буква
     */
    private static final String USERNAME_PATTERN = "^[а-яА-яёЁa-zA-Z][а-яА-яёЁa-zA-Z0-9-_\\.]{2,32}$";

    public UsernameValidator() {
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    @Override
    public boolean valid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
