package com.netcracker.controller.payment;

import com.netcracker.auth.AuthenticationSecurityService;
import com.netcracker.business.PriceService;
import com.netcracker.dto.ProductDto;
import com.netcracker.pojo.Order;
import com.netcracker.pojo.Product;
import com.netcracker.pojo.User;
import com.netcracker.service.OrderService;
import com.netcracker.service.ProductService;
import com.netcracker.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Log
@RequestMapping(value = "/product")
public class PaymentController {

    private ProductService productService;
    private OrderService orderService;
    private PriceService priceService;
    private AuthenticationSecurityService authenticationSecurityService;
    private UserService userService;

    @Autowired
    public PaymentController(ProductService productService,
                             OrderService orderService,
                             PriceService priceService,
                             AuthenticationSecurityService authenticationSecurityService,
                             UserService userService) {

        this.productService = productService;
        this.orderService = orderService;
        this.priceService = priceService;
        this.authenticationSecurityService = authenticationSecurityService;
        this.userService = userService;
    }

    @GetMapping(value = "/bucket/{id}")
    public String addProductDTOToBucket(@PathVariable("id") Long id,
                                        HttpSession session) {

        if (session.getAttribute("product") == null) {
            List<ProductDto> productDtos = new ArrayList<>();
            productDtos.add(new ProductDto(productService.getEntityById(id), 1));
            session.setAttribute("product", productDtos);
        } else {
            List<ProductDto> product = (List<ProductDto>) session.getAttribute("product");

            int index = isExist(id, session);

            if (index == -1)
                product.add(new ProductDto(this.productService.getEntityById(id), 1));
            else {
                int count = product.get(index).getCount() + 1;
                product.get(index).setCount(count);
            }

            session.setAttribute("product", product);
        }

        return "user/bucket";
    }

    private int isExist(Long id, HttpSession session) {
        List<ProductDto> products = (List<ProductDto>) session.getAttribute("product");

        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getProduct().getId(), id)) {
                return i;
            }
        }

        return -1;
    }

    @GetMapping(value = "/bucket/delete/{id}")
    public String deleteProductDTOFromBucket(@PathVariable(value = "id") Long id, HttpSession session) {
        List<ProductDto> products = (List<ProductDto>) session.getAttribute("product");

        int index = isExist(id, session);

        orderService.deleteProductFromBucket(products, index);

        session.setAttribute("product", products);

        return "user/bucket";
    }

    @GetMapping(value = "/bucket")
    public String getBucket() {
        return "user/bucket";
    }


    @GetMapping(value = "/orders")
    public String orderDetailsAboutProduct(Model model, HttpSession session, Order order) {
        List<ProductDto> products = (List<ProductDto>) session.getAttribute("product");

        String username = authenticationSecurityService.findLoggedInUsername();
        User user = userService.loadByUsername(username);
        double amount = priceService.totalPriceProduct(products);

        order = new Order();
        order.setAmount(amount);
        order.setUser(user);
        order.setOrderDate(new Date(System.currentTimeMillis()));

        Set<Product> productTests = new HashSet<>();
        for (ProductDto prod : products) {
            productTests.add(productService.getEntityById(prod.getProduct().getId()));
        }

        order.setProducts(productTests);

        orderService.addOrder(order, products);

        model.addAttribute("order", order);
        session.removeAttribute("product");

        return "user/order";
    }


}
