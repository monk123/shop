package com.netcracker;

import com.netcracker.pojo.Address;
import com.netcracker.pojo.Category;
import com.netcracker.pojo.Product;
import com.netcracker.pojo.User;
import com.netcracker.service.AddressService;
import com.netcracker.service.CategoryService;
import com.netcracker.service.ProductService;
import com.netcracker.service.UserService;
import com.netcracker.validator.productFormValidator.ProductFormValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */

@Controller @Log
@RequestMapping(value = "/admin")
public class AdminController {

    private ProductService productService;

    private CategoryService categoryService;

    private ProductFormValidator productFormValidator;

    private UserService userService;

    private AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setProductFormValidator(ProductFormValidator productFormValidator) {
        this.productFormValidator = productFormValidator;
    }

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public String listProduct(Model model, @RequestParam(value = "page", defaultValue = "0",
            required = false) Integer number) {

        model.addAttribute("product", new Product());

        model.addAttribute("products", productService.paginationProduct(0, 5));

        return "admin/productAdminList";
    }

    @RequestMapping(value = "/product/adminAddProductForm", method = RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("productForm", new Product());

        return "admin/adminAddProductForm";
    }

    @RequestMapping(value = "/product/adminAddProductForm", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("productForm") Product productForm,
                            BindingResult bindingResult, Model model) {
        productFormValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/adminAddProductForm";
        }

        Category category = categoryService.findCategoryByCategoryName(productForm.getCategory().getCategoryName());

        if (productForm.getId() == null) {
            category.add(productForm);
            productService.save(productForm);
        } else {
            category.add(productForm);
            productService.update(productForm);
        }

        return "redirect:/admin/product/list";
    }

    @RequestMapping(value = "/product/showById/{id}", method = RequestMethod.GET)
    public String getProductById(@PathVariable(value = "id") Long id, Product product,
                                 Model model) {

        product = productService.getEntityById(id);

        model.addAttribute("productForm", product);

        return "admin/showProductById";
    }

    @RequestMapping(value = "/product/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable(value = "id") Long id,  Model model) {

        Product product;

        if(id != null){
            product = productService.getEntityById(id);
        } else {
            product = new Product();
        }

        model.addAttribute("productForm", product);

        return "admin/adminAddProductForm";
    }

    @RequestMapping(value = "/product/edit/{id}", method = RequestMethod.POST)
    public String editProduct(@PathVariable(value = "id") Long id, @ModelAttribute Product product,
                              BindingResult bindingResult, Model model) {
        productFormValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/adminAddProductForm";
        }

        Category category = categoryService.findCategoryByCategoryName(product.getCategory().getCategoryName());

        category.add(product);

        productService.update(product);

        model.addAttribute("products", productService.paginationProduct(0, 10));

        return "admin/productAdminList";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(value = "id") Long id) {

        productService.delete(id);

        return "redirect:/admin/productAdminList";
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String userList(Model model) {

        model.addAttribute("users", userService.paginationUser(0, 10));

        return "admin/userAdminList";
    }
}
