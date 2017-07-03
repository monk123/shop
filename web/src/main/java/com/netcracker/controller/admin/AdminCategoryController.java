package com.netcracker.controller.admin;

import com.netcracker.pojo.Category;
import com.netcracker.service.CategoryService;
import com.netcracker.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Log
@Controller
@RequestMapping(value = "admin/product/category")
public class AdminCategoryController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public AdminCategoryController(ProductService productService,
                                   CategoryService categoryService) {

        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/addCategory")
    public String addCategoryByPage(Model model) {
        model.addAttribute("newCategory", new Category());

        return "admin/addCategoryForm";
    }

    @PostMapping(value = "/addCategory")
    public String addCategoryByPage(@ModelAttribute(value = "newCategory") Category category,
                                    BindingResult bindingResult, Model model,
                                    HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "admin/addCategoryForm";
        }

        categoryService.save(category);

        return "redirect:" + request.getRequestURL();
    }
}
