package com.netcracker;

import com.netcracker.pojo.Product;
import com.netcracker.pojo.User;
import com.netcracker.service.*;
import com.netcracker.validator.accountUserFormValidator.AccountUserFormValidator;
import com.netcracker.validator.loginUserFormValidator.LoginUserFormValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller @Log
public class UserController {

    private ProductService productService;
    private CategoryService categoryService;
    private UserService userService;
    private LoginUserFormValidator loginUserFormValidator;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AccountUserFormValidator accountUserFormValidator;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setLoginUserFormValidator(LoginUserFormValidator loginUserFormValidator) {
        this.loginUserFormValidator = loginUserFormValidator;
    }

    @RequestMapping(value = "/product/category/{name}", method = RequestMethod.GET)
    public String getCategory(@PathVariable(value = "name") String name, Model model) {

        List<Product> list = productService.getProductByCategoryName(name, 0, 10);

        if (!list.isEmpty()) {
            model.addAttribute("products", list);
        }

        List<String> categories = categoryService.getCategoryByUniqueName();

        if (!categories.isEmpty()) {
            model.addAttribute("categoryName", categories);
        }

        model.addAttribute("product", new Product());

        return "user/productCategoryPage";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {

        model.addAttribute("categoryName", categoryService.getCategoryByUniqueName());
        model.addAttribute("products", productService.paginationProduct(0, 10));

        return "welcome";
    }

    @RequestMapping(value = "/product/info/{id}", method = RequestMethod.GET)
    public String userProductDataPage(@PathVariable(value = "id") Long id,  Model model) {

        Product product = productService.getEntityById(id);

        if (product != null) {

            model.addAttribute("product", product);

            return "/user/productInfo";
        }

        return null;
    }

    @RequestMapping(value = "/user/info/edit/{id}", method = RequestMethod.GET)
    public String userInfoPage(@PathVariable(value = "id") Long id, Model model) {
        User userForm = null;

        if (id != null) {
            userForm = userService.getEntityById(id);
        }

        model.addAttribute("userForm", userForm);

        return "user/userPageForm";
    }

    @RequestMapping(value = "/user/info/edit/{id}", method = RequestMethod.POST)
    public String userInfoPage(@PathVariable(value = "id") Long id, @ModelAttribute("userForm") User user,
                               Model model, BindingResult result) {
        accountUserFormValidator.validate(user, result);

        if (result.hasErrors()) {
            return "user/userPageForm";
        }

        userService.insertInformationAboutUser(user);

        model.addAttribute("user", user);

        return "user/userPage";
    }

    @RequestMapping(value = "/user/info/{name}", method = RequestMethod.GET)
    public String getInfo(@PathVariable(value = "name") String name, Model model) {
        User user = null;

        if (name != null) {
            user = userService.loadByUsername(name);
        } else {
            user = new User();
        }

        model.addAttribute("user", user);

        return "user/userPage";
    }
}
