package com.StrangerBookstore.service;


import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Roles;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findbyId(Integer id){
        return customerRepository.findById(id);
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Integer id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();

            // Update common fields
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setEmail(customer.getEmail());

            // Update roles if different
            if (!existingCustomer.getRoles().equals(customer.getRoles())) {
                Optional<Roles> rolesOptional = roleRepository.findById(customer.getRoles().getRoleId());

                if (rolesOptional.isPresent()) {
                    Roles roles = rolesOptional.get();
                    existingCustomer.setRoles(roles);
                } else {
                    return null; // Handle the case when roles are not found
                }
            }

            return customerRepository.save(existingCustomer);
        } else {
            return null; // Handle the case when customer is not found
        }
    }


    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
