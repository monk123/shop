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
public class PasswordValidator implements ValidatorService{

    private Pattern pattern;

    /**
     * The password's first character must be a letter, it must contain at least
     * 4 characters and no more than 15 characters and no characters other than letters,
     * numbers and the underscore may be used
     */
    private static final String PASSWORD_PATTERN = "^[a-zA-Z]\\w{3,14}$";

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean valid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
