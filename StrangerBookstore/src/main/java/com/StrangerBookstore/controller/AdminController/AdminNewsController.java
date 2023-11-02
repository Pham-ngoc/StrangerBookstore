package com.StrangerBookstore.controller.AdminController;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminNewsController {

    @GetMapping("/news")
    public String news(Model model){
        return "Admin-view/admin-news.html";
    }
}