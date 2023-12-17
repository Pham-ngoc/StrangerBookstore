package com.StrangerBookstore.controller.AdminController;


import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.repository.CategoryRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AdminCateController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getAll(){
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Categories> getByProductId(@PathVariable("categoryId") int categoryId){
        Optional<Categories> categories = categoryRepository.findById(categoryId);
        return ResponseEntity.ok(categories.get());
    }
}
