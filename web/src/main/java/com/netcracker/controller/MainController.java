package com.netcracker.controller;

import com.netcracker.dto.PriceDto;
import com.netcracker.page.IPage;
import com.netcracker.pojo.Product;
import com.netcracker.service.*;
import com.netcracker.validator.priceFormValidator.PriceFormValidatorImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Log
@RequestMapping("/welcome")
public class MainController {

    private ProductService productService;
    private CategoryService categoryService;
    private IPage iPage;
    private PriceFormValidatorImpl validator;

    private static int TOTAL = 6;

    @Autowired
    public MainController(ProductService productService,
                          CategoryService categoryService,
                          IPage iPage,
                          PriceFormValidatorImpl validator) {

        this.productService = productService;
        this.categoryService = categoryService;
        this.iPage = iPage;
        this.validator = validator;
    }

    @GetMapping(value = "/{page}")
    public String list(@PathVariable("page") Integer page,
                       Model model, HttpServletRequest request) {

        int size = iPage.getSizeProduct(TOTAL);

        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("dto", new PriceDto());
        model.addAttribute("categoryName", categoryService.getCategoryByUniqueName());
        model.addAttribute("products", productService.paginationProduct(iPage.getPage(page, TOTAL), TOTAL));

        return "welcome";
    }

    @GetMapping(value = "/filter/{page}")
    public String filter(@PathVariable Integer page,
                         @ModelAttribute(value = "dto") PriceDto dto,
                         BindingResult bindingResult, Model model) {

        validator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/welcome";
        }

        List<Product> products = productService.getProductByPrice(dto,  iPage.getPage(page, TOTAL), TOTAL);

        model.addAttribute("filterSize", iPage.getSizeProductByPrice(dto, TOTAL));
        model.addAttribute("priceFrom", dto.getPriceFrom());
        model.addAttribute("priceTo", dto.getPriceTo());
        model.addAttribute("filterPage", page);
        model.addAttribute("categoryName", categoryService.getCategoryByUniqueName());
        model.addAttribute("products", products);

        return "welcome";
    }

    @GetMapping(value = "/search/{page}", params = "name")
    public String search(@PathVariable(value = "page") Integer page,
                         @RequestParam(value = "name") String name,
                         Model model) {

        int size = iPage.getSizeFindProduct(name, TOTAL);

        List<Product> products = productService.findProduct(name, iPage.getPage(page, TOTAL), TOTAL);

        model.addAttribute("searchPage", page);
        model.addAttribute("searchSize", size);
        model.addAttribute("params", name);
        model.addAttribute("dto", new PriceDto());
        model.addAttribute("categoryName", categoryService.getCategoryByUniqueName());
        model.addAttribute("products", products);

        return "welcome";
    }

    @GetMapping(value = "/{name}/{page}")
    public String getProductByCategory(@PathVariable(value = "name") String name,
                                       @PathVariable(value = "page") Integer page,
                                       Model model) {

        model.addAttribute("sizeCategory", iPage.getSizeProductWithString(name, TOTAL));

        List<Product> list = productService.getProductByCategoryName(name, iPage.getPage(page, TOTAL), TOTAL);

        if (!list.isEmpty()) {
            model.addAttribute("products", list);
        }

        model.addAttribute("pageCategory", page);
        model.addAttribute("category", name);
        model.addAttribute("dto", new PriceDto());
        model.addAttribute("categoryName", categoryService.getCategoryByUniqueName());

        return "welcome";
    }
}
