package com.netcracker;

import com.netcracker.auth.AuthenticationSecurityService;
import com.netcracker.auth.AuthenticationUserService;
import com.netcracker.pojo.Address;
import com.netcracker.pojo.User;
import com.netcracker.service.AddressService;
import com.netcracker.service.ProductService;
import com.netcracker.service.UserService;
import com.netcracker.validator.loginUserFormValidator.LoginUserFormValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller @Log
public class LoginController {

    private AuthenticationUserService authenticationUserService;
    private AuthenticationSecurityService authenticationSecurityService;
    private LoginUserFormValidator loginUserFormValidator;

    @Autowired
    public void setAuthenticationUserService(AuthenticationUserService authenticationUserService) {
        this.authenticationUserService = authenticationUserService;
    }

    @Autowired
    public void setLoginUserFormValidator(LoginUserFormValidator loginUserFormValidator) {
        this.loginUserFormValidator = loginUserFormValidator;
    }

    @Autowired
    public void setAuthenticationSecurityService(AuthenticationSecurityService authenticationSecurityService) {
        this.authenticationSecurityService = authenticationSecurityService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        loginUserFormValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        authenticationUserService.save(userForm);

        authenticationSecurityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }
}
