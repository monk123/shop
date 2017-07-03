package com.netcracker.controller.admin;

import com.netcracker.page.IPage;
import com.netcracker.pojo.User;
import com.netcracker.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log
@RequestMapping("/admin/user")
public class AdminController {

    private UserService userService;
    private IPage iPage;
    
    private static final int TOTAL = 6;

    @Autowired
    public AdminController(UserService userService, IPage iPage) {
        this.userService = userService;
        this.iPage = iPage;
    }

    @GetMapping(value = "/list/{page}")
    public String userList(@PathVariable Integer page, Model model) {

        model.addAttribute("size", iPage.getSizeUser(TOTAL));
        model.addAttribute("users", userService.paginationUser(iPage.getPage(page, TOTAL), TOTAL));

        return "admin/userAdminList";
    }

    @GetMapping(value = "/blocked/{id}")
    public String blockedUser(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.getEntityById(id);

        if (user != null && user.isActive()) {

        }

        return null;
    }

    @GetMapping(value = "/unblocked/{id}")
    public String unblockedUser() {

        return null;
    }
}
