package com.StrangerBookstore.controller.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminCategoriesController {

    @GetMapping("/categories")
    public String categories(Model model){
        return "Admin-view/admin-categories.html";
    }
}
