package com.StrangerBookstore.service;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Roles;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean createNewCustomer(Customer customer) {
        boolean isCreate = false;
        Roles roles = roleRepository.getByRoleName("USER");
        customer.setRoles(roles);
        customer.setStatus("Open");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("anonymousUser");
        Customer createCustomer = customerRepository.save(customer);
        if(createCustomer != null && createCustomer.getCustomerId() >= 0 ) {
            isCreate = true;
        }
        return isCreate;
    }
}
