package com.netcracker.controller.user;

import com.netcracker.pojo.User;
import com.netcracker.service.UserService;
import com.netcracker.validator.accountUserFormValidator.AccountUserFormValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Log
@RequestMapping(value = "/welcome/user/")
public class UserController {

    private UserService userService;
    private AccountUserFormValidator accountUserFormValidator;

    @Autowired
    public UserController(UserService userService,
                          AccountUserFormValidator accountUserFormValidator) {

        this.userService = userService;
        this.accountUserFormValidator = accountUserFormValidator;
    }

    @GetMapping(value = "/edit/{id}")
    public String userInfoPage(@PathVariable(value = "id") Long id, Model model) {
        User userForm = null;

        if (id != null) {
            userForm = userService.getEntityById(id);
        }

        model.addAttribute("userForm", userForm);

        return "user/userPageForm";
    }

    @PostMapping(value = "/edit/{id}")
    public String userInfoPage(@PathVariable(value = "id") Long id,
                               @ModelAttribute("userForm") User userForm,
                               BindingResult result) {

        accountUserFormValidator.validate(userForm, result);

        if (result.hasErrors()) {
            return "user/userPageForm";
        }

        userService.editUserInformation(userForm);

        return "redirect:/welcome/user/" + userForm.getUsername();
    }

    @GetMapping(value = "/{name}")
    public String getInfo(@PathVariable(value = "name") String name,
                          Model model) {
        User user = null;

        if (name != null) {
            user = userService.loadByUsername(name);
        } else {
            user = new User();
        }

        model.addAttribute("userForm", user);

        return "user/userPage";
    }


}
