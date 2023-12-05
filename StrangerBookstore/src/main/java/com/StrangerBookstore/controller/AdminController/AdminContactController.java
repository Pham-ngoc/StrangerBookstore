package com.StrangerBookstore.controller.AdminController;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
