package com.StrangerBookstore.controller.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class NewsController {
    @RequestMapping("/new")
    public String homeNew(Model model){
        return ("User-view/News/News.html");
    }
    @RequestMapping("/newdetail")
    public String newDetail(Model model){
        return ("User-view/News/News-detail.html");
    }

}
