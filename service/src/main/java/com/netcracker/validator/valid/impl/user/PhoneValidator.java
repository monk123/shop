package com.netcracker.validator.valid.impl.user;

import com.netcracker.validator.valid.ValidatorService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component @Log
public class PhoneValidator implements ValidatorService {

    private Pattern pattern;

    private static final String PHONE_PATTERN = "d{7}";

    public PhoneValidator() {
        this.pattern = Pattern.compile(PHONE_PATTERN);
    }

    @Override
    public boolean valid(final String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
