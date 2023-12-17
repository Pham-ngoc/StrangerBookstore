package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.model.Roles;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.repository.RoleRepository;
import com.StrangerBookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminCustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/role")
    public ResponseEntity<List<Roles>> role(Model model){
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> customer(Model model){
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getByProductId(@PathVariable("customerId") int customerId){
        return ResponseEntity.ok(customerRepository.findByCustomerId(customerId));
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> create(@RequestBody Customer customer, @RequestParam("file") MultipartFile file) throws Exception {
        Customer createCustomer = new Customer();
        createCustomer.setCustomerName(customer.getCustomerName());
        createCustomer.setPhoneNumber(customer.getPhoneNumber());
        createCustomer.setEmail(customer.getEmail());
        createCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
        createCustomer.setPicture(customerService.getImageName(file));
        createCustomer.setRoles(customer.getRoles());
        createCustomer.setStatus("Open");
        customerRepository.save(createCustomer);
        return ResponseEntity.ok(createCustomer);
    }
}
