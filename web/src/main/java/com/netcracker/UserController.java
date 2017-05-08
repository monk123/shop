package com.netcracker;

import com.netcracker.pojo.Product;
import com.netcracker.service.CategoryService;
import com.netcracker.service.OrderService;
import com.netcracker.service.ProductService;
import com.netcracker.validator.productBuyValidator.ProductBuyFormValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller @Log
@SessionAttributes("product")
public class UserController {

    private ProductService productService;

    private OrderService orderService;

    private CategoryService categoryService;

    private ProductBuyFormValidator productBuyFormValidator;

    @Autowired
    public void setProductBuyFormValidator(ProductBuyFormValidator productBuyFormValidator) {
        this.productBuyFormValidator = productBuyFormValidator;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/bucket", method = {RequestMethod.GET, RequestMethod.POST})
    public String addBucket(@ModelAttribute("product") Product product,
                            BindingResult bindingResult, Model model) {

        productBuyFormValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/productInfo";
        }



        model.addAttribute("product", product);

        return "user/bucket";
    }

    @RequestMapping(value = "product/category/{name}", method = RequestMethod.GET)
    public String getCategory(@PathVariable String name, Model model) {

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
    public String userProductDataPage(@PathVariable Long id, Model model) {

        Product product = productService.getEntityById(id);

        if (product != null) {

            model.addAttribute("product", product);

            return "/user/productInfo";
        }

        return null;
    }

    @RequestMapping(value = "/order-details-{number}", method = RequestMethod.GET)
    public String order(@PathVariable Integer number, Model model) {

        return "user/order";
    }
}
