package com.netcracker.validator.validate.impl.address;

import com.netcracker.validator.validate.ValidatorService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of {@link ValidatorService}
 *
 * @author Ayupov Vadim
 */

@Log
@Component
public class RegionValidator implements ValidatorService {

    private Pattern pattern;

    private static final String PATTERN_REGION = "^.*[а-яА-ЯёЁa-zA-Z]{2,30}$";

    public RegionValidator() {
        this.pattern = Pattern.compile(PATTERN_REGION);
    }

    @Override
    public boolean valid(String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
