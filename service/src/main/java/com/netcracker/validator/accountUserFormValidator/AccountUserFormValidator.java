package com.netcracker.validator.accountUserFormValidator;

import com.netcracker.pojo.User;
import com.netcracker.service.UserService;
import com.netcracker.validator.validate.impl.address.CityValidator;
import com.netcracker.validator.validate.impl.address.CountryValidator;
import com.netcracker.validator.validate.impl.address.RegionValidator;
import com.netcracker.validator.validate.impl.address.StreetValidator;
import com.netcracker.validator.validate.impl.user.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Implementation of {@link Validator} interface
 *
 * @author Ayupov Vadim
 */

@Log
@Component
public class AccountUserFormValidator implements Validator {

    private UsernameValidator usernameValidator;
    private LastNameValidator lastNameValidator;
    private EmailValidator emailValidator;
    private PhoneValidator phoneValidator;
    private UserService userService;
    private CountryValidator countryValidator;
    private RegionValidator regionValidator;
    private CityValidator cityValidator;
    private StreetValidator streetValidator;

    @Autowired
    public AccountUserFormValidator(UsernameValidator usernameValidator,
                                    LastNameValidator lastNameValidator,
                                    EmailValidator emailValidator,
                                    PhoneValidator phoneValidator,
                                    UserService userService,
                                    CountryValidator countryValidator,
                                    RegionValidator regionValidator,
                                    CityValidator cityValidator,
                                    StreetValidator streetValidator) {

        this.usernameValidator = usernameValidator;
        this.lastNameValidator = lastNameValidator;
        this.emailValidator = emailValidator;
        this.phoneValidator = phoneValidator;
        this.userService = userService;
        this.countryValidator = countryValidator;
        this.regionValidator = regionValidator;
        this.cityValidator = cityValidator;
        this.streetValidator = streetValidator;
    }

    /**
     * метод supports(Class<?> clazz) регистрирует какие классы должны поддерживать данную валидацию
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    /**
     * метод validate(Object obj, Errors errors) собственно и осуществляет валидацию,
     * тут мы описываем правила валидации,
     * а точней в каком случае данные будут считаться не валидными
     *
     * @param obj
     * @param errors
     */
    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (!usernameValidator.valid(user.getUsername())) {
            errors.rejectValue("username", "Username.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        if (!lastNameValidator.valid(user.getLastName())) {
            errors.rejectValue("lastName", "LastName.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (!emailValidator.valid(user.getEmail())) {
            errors.rejectValue("email", "Email.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Required");
        if (!phoneValidator.valid(user.getPhone())) {
            errors.rejectValue("phone", "Phone.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.country", "Required");
        if (!countryValidator.valid(user.getAddress().getCountry())) {
            errors.rejectValue("address.country", "Country.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.region", "Required");
        if (!regionValidator.valid(user.getAddress().getRegion())) {
            errors.rejectValue("address.region", "Region.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "Required");
        if (!cityValidator.valid(user.getAddress().getCity())) {
            errors.rejectValue("address.city", "City.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.street", "Required");
        if (!streetValidator.valid(user.getAddress().getStreet())) {
            errors.rejectValue("address.street", "Street.userForm.error");
        }
    }
}
