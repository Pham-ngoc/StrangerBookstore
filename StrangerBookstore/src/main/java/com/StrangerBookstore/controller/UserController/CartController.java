package com.StrangerBookstore.controller.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @GetMapping("/cart")
    public String cartView (Model model){
        return "User-view/cart.html";
    }
}
