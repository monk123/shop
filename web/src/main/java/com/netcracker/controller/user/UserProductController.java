package com.netcracker.controller.user;

import com.netcracker.auth.AuthenticationSecurityService;
import com.netcracker.pojo.Comment;
import com.netcracker.pojo.Product;
import com.netcracker.service.CommentService;
import com.netcracker.service.ProductService;
import com.netcracker.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@Log
@RequestMapping("/welcome")
public class UserProductController {

    private ProductService productService;
    private CommentService commentService;
    private AuthenticationSecurityService authenticationSecurityService;
    private UserService userService;

    @Autowired
    public UserProductController(ProductService productService,
                                 CommentService commentService,
                                 AuthenticationSecurityService authenticationSecurityService,
                                 UserService userService) {
        this.productService = productService;
        this.commentService = commentService;
        this.authenticationSecurityService = authenticationSecurityService;
        this.userService = userService;
    }

    @GetMapping(value = "/info/{id}")
    public String productInfoByPage(@PathVariable(value = "id") Long id,
                                    Model model) {

        Product product = productService.getEntityById(id);

        if (product != null) {

            model.addAttribute("comments", commentService.getAllCommentsByProductSQL(id));
            model.addAttribute("commit", new Comment());
            model.addAttribute("username", authenticationSecurityService.findLoggedInUsername());
            model.addAttribute("product", product);

            return "/user/productInfo";
        }

        return null;
    }

    @PostMapping(value = "/add/comment/{productId}")
    public String addCommentAboutProductByPage(@PathVariable(value = "productId") Long id,
                                               @ModelAttribute(value = "commit") Comment comment,
                                               Model model, BindingResult bindingResult,
                                               HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/user/productInfo";
        }

        String prev = request.getHeader("Referer");

        Product product = productService.getEntityById(id);
        comment.setProduct(product);
        commentService.addComment(comment);

        return "redirect:" + prev;
    }
}
