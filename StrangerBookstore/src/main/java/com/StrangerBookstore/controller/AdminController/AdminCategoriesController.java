package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.repository.CategoriesRepository;
import com.StrangerBookstore.service.CategoriesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminCategoriesController {
    @Autowired
    CategoriesService service;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> categories(Model model){
        return ResponseEntity.ok(categoriesRepository.findAll());
    }

    @GetMapping("/categories/{id}")
    public String getfindone(@PathVariable("id") Integer id)
    {
        Categories category = service.findbyId(id);
        return category.getCategoryName();
    }

    @PostMapping("/categories")
    public ResponseEntity<Categories> create(@RequestBody Categories categories) {
        Categories createdCategory = service.create(categories);
        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable("id") Integer id, @RequestBody Categories category) {
        if(service. findbyId (id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.update(id, category);
        }
        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/categories/{id}")
    public void deletecategory(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}

