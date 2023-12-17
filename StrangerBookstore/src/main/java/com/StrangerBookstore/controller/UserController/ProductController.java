package com.StrangerBookstore.controller.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController{
    @RequestMapping("/product-detail")
    public String proDetail(Model model){

        return "User-view/product-detail.html";
    }


}
