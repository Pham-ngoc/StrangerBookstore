package com.StrangerBookstore.controller.AdminController;
import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Roles;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.repository.RoleRepository;
import com.StrangerBookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.AttributedString;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminCustomerController {

    @Autowired
    CustomerService service;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/StrangerBookstore/src/main/resources/static/images";
    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> categories(Model model){
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getcustomer(@PathVariable Integer id) {
        Optional<Customer> customerOptional = service.findById(id);
        return customerOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/customer")
    public ResponseEntity<Customer> create(@RequestBody Customer customer)  {
//        System.out.println(customer.getPicture());
        Customer createdCustomer = service.create(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updatecustomer(@PathVariable("id") int id, @RequestBody Customer Customer) {
        if(service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            System.out.println(Customer.getPicture());
            customerRepository.save(Customer);
        }
        return ResponseEntity.ok(Customer);
    }


    @DeleteMapping("/customer/{id}")
    public void deletecustomer(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Roles>> role(Model model){
        return ResponseEntity.ok(roleRepository.findAll());
    }

}

