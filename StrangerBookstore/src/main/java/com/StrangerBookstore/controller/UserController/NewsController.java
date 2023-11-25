package com.StrangerBookstore.controller.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class NewsController {
    @RequestMapping("/news")
    public String homeNew(Model model){
        return ("User-view/news.html");
    }
    @RequestMapping("/news-detail")
    public String newDetail(Model model){
        return ("User-view/news-detail.html");
    }

}
