package com.StrangerBookstore.controller.AdminController;
import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminCustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/customer")
    public ResponseEntity<List<Object>> customer(Model model){
        List<Customer> cus= service.findAll();
        return ResponseEntity.ok(Collections.singletonList(cus));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getcustomer(@PathVariable Integer id) {
        Optional<Customer> customerOptional = service.findbyId(id);
        return customerOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer createdCustormer = service.create(customer);
        return ResponseEntity.ok(createdCustormer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updatecustomer(@PathVariable("id") Integer id, @RequestBody Customer Customer) {
        if(service. findbyId (id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.update(id, Customer);
        }
        return ResponseEntity.ok(Customer);
    }


    @DeleteMapping("/customer/{id}")
    public void deletecustomer(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}

