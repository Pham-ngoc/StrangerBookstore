package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.ContactUs;
import com.StrangerBookstore.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminContactController {

    @Autowired
    ContactService contactservice;

    @GetMapping("/contact")
    public ResponseEntity<List<ContactUs>> categories(Model model){
        return ResponseEntity.ok(contactservice.findAll());
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<ContactUs> updateCategory(@PathVariable("id") Integer id, @RequestBody ContactUs contactUs) {
        if(contactservice. findbyId (id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            contactservice.update(id, contactUs);
        }
        return ResponseEntity.ok(contactUs);
    }
}
