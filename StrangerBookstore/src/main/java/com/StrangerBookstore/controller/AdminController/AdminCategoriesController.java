package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminCategoriesController {
    @Autowired
    CategoriesService service;

    @GetMapping("/categories")
    public ResponseEntity<List<Object>> categories(Model model){
        List<Categories> cat= service.findAll();
        return ResponseEntity.ok(Collections.singletonList(cat));
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

