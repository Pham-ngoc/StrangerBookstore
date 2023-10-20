package com.StrangerBookstore.controller.UserController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/home")
    public String home(Model model){
        return "User-view/home.html";
    }
}
