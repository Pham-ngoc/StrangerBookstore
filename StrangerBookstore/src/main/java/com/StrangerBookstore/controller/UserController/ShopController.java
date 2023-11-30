package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping("/shop")
    public String shop(Model model){
        List<Categories> listCate = categoryRepository.findAll();
        model.addAttribute("listCate", listCate);
        return "User-view/shop.html";
    }
}
