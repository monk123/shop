package com.netcracker.validator.loginFormValidator;

import com.netcracker.auth.AuthenticationUserService;
import com.netcracker.pojo.User;
import com.netcracker.validator.validate.impl.user.PasswordValidator;
import com.netcracker.validator.validate.impl.user.UsernameValidator;
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
public class LoginFormValidator implements Validator {

    private AuthenticationUserService authenticationUserService;
    private PasswordValidator passwordValidator;
    private UsernameValidator usernameValidator;

    @Autowired
    public LoginFormValidator(AuthenticationUserService authenticationUserService,
                              PasswordValidator passwordValidator,
                              UsernameValidator usernameValidator) {

        this.authenticationUserService = authenticationUserService;
        this.passwordValidator = passwordValidator;
        this.usernameValidator = usernameValidator;
    }

    /**
     * метод supports(Class<?> clazz) регистрирует какие классы должны поддерживать данную валидацию
     *
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
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (authenticationUserService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32 ||
                !passwordValidator.valid(user.getPassword())) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
