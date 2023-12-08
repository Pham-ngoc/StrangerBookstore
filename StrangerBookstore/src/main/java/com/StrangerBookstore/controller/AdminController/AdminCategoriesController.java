package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCategoriesController {

    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> category(Model model){
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}
