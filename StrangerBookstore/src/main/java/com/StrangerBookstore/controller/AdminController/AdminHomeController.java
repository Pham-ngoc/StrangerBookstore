package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Report;
import com.StrangerBookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AdminHomeController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/report")
    public ResponseEntity<List<Report>> getReport(){
        return ResponseEntity.ok(customerRepository.getCustomerReports());
    }


}
