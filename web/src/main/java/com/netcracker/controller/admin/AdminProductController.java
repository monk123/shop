package com.netcracker.controller.admin;

import com.netcracker.page.IPage;
import com.netcracker.pojo.Product;
import com.netcracker.service.CategoryService;
import com.netcracker.service.ProductService;
import com.netcracker.validator.productFormValidator.ProductFormValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/admin/product")
public class AdminProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private ProductFormValidator productFormValidator;
    private IPage iPage;
    private static final int TOTAL = 6;

    @Autowired
    public AdminProductController(ProductService productService,
                                  CategoryService categoryService,
                                  ProductFormValidator productFormValidator,
                                  IPage iPage) {

        this.productService = productService;
        this.categoryService = categoryService;
        this.productFormValidator = productFormValidator;
        this.iPage = iPage;
    }

    @GetMapping(value = "/list/{page}")
    public String listProduct(Model model, @PathVariable Integer page) {

        model.addAttribute("size", iPage.getSizeProduct(TOTAL));
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.paginationProduct(iPage.getPage(page, TOTAL), TOTAL));

        return "admin/productAdminList";
    }

    @GetMapping(value = "/adminAddProductForm")
    public String addProduct(Model model) {
        model.addAttribute("productForm", new Product());
        model.addAttribute("categories", categoryService.getCategoryByUniqueName());

        return "admin/adminAddProductForm";
    }

    @PostMapping(value = "/adminAddProductForm")
    public String addProduct(@ModelAttribute("productForm") Product productForm,
                             BindingResult bindingResult, Model model,
                             HttpServletRequest request) {

        productFormValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/adminAddProductForm";
        }

        productService.addProduct(productForm);

        return "redirect:/admin/product/showById/" + productForm.getId();
    }

    @GetMapping(value = "/showById/{id}")
    public String getProductById(@PathVariable(value = "id") Long id,
                                 Product product, Model model) {

        product = productService.getEntityById(id);

        model.addAttribute("productForm", product);

        return "admin/showProductById";
    }

    @GetMapping(value = "/edit/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,  Model model) {

        Product product;

        if(id != null){
            product = productService.getEntityById(id);
        } else {
            product = new Product();
        }

        model.addAttribute("productForm", product);
        model.addAttribute("categories", categoryService.getCategoryByUniqueName());
        return "admin/adminAddProductForm";
    }

    @PostMapping(value = "/edit/{id}")
    public String editProduct(@PathVariable(value = "id") Long id,
                              @ModelAttribute(value = "productForm") Product productForm,
                              BindingResult bindingResult, Model model,
                              HttpServletRequest request) {

        productFormValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/adminAddProductForm";
        }

        productService.editProduct(productForm);

        return "redirect:/admin/product/showById/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id,
                                HttpServletRequest request) {

        String prev = request.getHeader("Referer");

        productService.delete(id);

        return "redirect:" + prev;
    }
}
