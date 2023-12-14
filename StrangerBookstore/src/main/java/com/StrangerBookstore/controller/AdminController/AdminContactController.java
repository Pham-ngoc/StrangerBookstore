package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.ContactUs;
import com.StrangerBookstore.service.Contactservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminContactController {

    @Autowired
    Contactservice contactservice;

    @GetMapping("/contact")
    public ResponseEntity<List<ContactUs>> categories(Model model){
        return ResponseEntity.ok(contactservice.findAll());
    }
}
