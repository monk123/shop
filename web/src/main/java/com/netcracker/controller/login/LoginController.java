package com.netcracker.controller.login;

import com.netcracker.auth.AuthenticationSecurityService;
import com.netcracker.auth.AuthenticationUserService;
import com.netcracker.pojo.User;
import com.netcracker.validator.loginFormValidator.LoginFormValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Log
public class LoginController {

    private AuthenticationUserService authenticationUserService;
    private AuthenticationSecurityService authenticationSecurityService;
    private LoginFormValidator loginFormValidator;

    @Autowired
    public LoginController(AuthenticationUserService authenticationUserService,
                           AuthenticationSecurityService authenticationSecurityService,
                           LoginFormValidator loginFormValidator) {

        this.authenticationUserService = authenticationUserService;
        this.authenticationSecurityService = authenticationSecurityService;
        this.loginFormValidator = loginFormValidator;
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        loginFormValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        authenticationUserService.save(userForm);

        authenticationSecurityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome/1";
    }

    @GetMapping(value = "/login")
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
