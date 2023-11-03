package com.StrangerBookstore.controller.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
    @RequestMapping ("/aboutus/about")
    public String about(Model model){
        return "User-view/AboutUs/About.html";
    }

    @RequestMapping("/aboutus/contact")
    public String contact(){
        return "User-view/AboutUs/Contact.html";
    }
}
