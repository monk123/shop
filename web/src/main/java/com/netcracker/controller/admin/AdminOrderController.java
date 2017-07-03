package com.netcracker.controller.admin;

import com.netcracker.pojo.Order;
import com.netcracker.pojo.Product;
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

import java.util.List;

@Log
@Controller
@RequestMapping(value = "/admin/product/order")
public class AdminOrderController {

    private OrderService orderService;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public AdminOrderController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public String listOrdersByPage(Model model) {
        model.addAttribute("orders", orderService.getAllEntities());
        return "admin/orderAdminList";
    }

    @GetMapping(value = "/info/{orderId}")
    public String orderInfo(@PathVariable("orderId") Long id,  Model model) {
        List<Product> productList = productService.getProductByOrderId(id);
        model.addAttribute("productList", productList);
        return "admin/orderPageInfo";
    }
}
