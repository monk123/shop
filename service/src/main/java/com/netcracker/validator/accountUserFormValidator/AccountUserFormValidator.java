package com.netcracker.validator.accountUserFormValidator;

import com.netcracker.pojo.User;
import com.netcracker.validator.valid.impl.user.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component @Log
public class AccountUserFormValidator implements Validator {

    private UsernameValidator usernameValidator;
    private LastNameValidator lastNameValidator;
    private EmailValidator emailValidator;
    private PhoneValidator phoneValidator;
    private PasswordValidator passwordValidator;

    @Autowired
    public void setUsernameValidator(UsernameValidator usernameValidator) {
        this.usernameValidator = usernameValidator;
    }

    @Autowired
    public void setLastNameValidator(LastNameValidator lastNameValidator) {
        this.lastNameValidator = lastNameValidator;
    }

    @Autowired
    public void setEmailValidator(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @Autowired
    public void setPhoneValidator(PhoneValidator phoneValidator) {
        this.phoneValidator = phoneValidator;
    }

    @Autowired
    public void setPasswordValidator(PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Username.userForm.error");
        if (!usernameValidator.valid(user.getUsername())) {
            errors.rejectValue("username", "Username.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "LastName.userForm.error");
        if (!lastNameValidator.valid(user.getLastName())) {
            errors.rejectValue("lastNAme", "LastName.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Email.userForm.error");
        if (!emailValidator.valid(user.getEmail())) {
            errors.rejectValue("email", "Email.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Phone.userForm.error");
        if (!phoneValidator.valid(user.getPhone())) {
            errors.rejectValue("email", "Phone.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Password.userForm.error");
        if (!passwordValidator.valid(user.getPassword())) {
            errors.rejectValue("password", "Password.userForm.error");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPasswrod","ConfirmPassword.userForm.error");
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "ConfirmPassword.userForm.error");
        }
    }
}
