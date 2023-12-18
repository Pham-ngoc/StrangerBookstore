package com.StrangerBookstore.service;


import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Roles;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Autowired
    ResourceLoader resourceLoader;

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

    public Optional<Customer> findById(Integer id){
        return customerRepository.findById(id);
    }

    public Customer create(Customer customer) {
        Roles roles = roleRepository.findByRoleId(customer.getRoles().getRoleId());
        customer.setStatus("Open");
        System.out.println(customer.getPassword());
        customer.setRoles(roles);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setPicture(customer.getPicture());
        return customerRepository.save(customer);
    }

//    public Customer update(Integer id, Customer customer) {
//        Optional<Customer> customerOptional = customerRepository.findById(id);
//
//        if (customerOptional.isPresent()) {
//            Customer existingCustomer = customerOptional.get();
//
//            // Update common fields
//            existingCustomer.setCustomerName(customer.getCustomerName());
//            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
//            existingCustomer.setEmail(customer.getEmail());
//            existingCustomer.setPicture(customer.getPicture());
//
//            // Update roles if different
//            if (!existingCustomer.getRoles().equals(customer.getRoles())) {
//                Optional<Roles> rolesOptional = roleRepository.findById(customer.getRoles().getRoleId());
//
//                if (rolesOptional.isPresent()) {
//                    Roles roles = rolesOptional.get();
//                    existingCustomer.setRoles(roles);
//                } else {
//                    return null; // Handle the case when roles are not found
//                }
//            }
//
//            return customerRepository.save(existingCustomer);
//        } else {
//            return null; // Handle the case when customer is not found
//        }
//    }


    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    public String getImageName(@RequestParam("customer_pic") MultipartFile file) {
        String filename = "";
        try {
            String uploadRootPath = resourceLoader.getResource("classpath:static/customer_pic/").getFile()
                    .getAbsolutePath();

            if (!Files.exists(Paths.get(uploadRootPath))) {
                Files.createDirectories(Paths.get(uploadRootPath));
            }

            filename = file.getOriginalFilename();
            Path filePath = Paths.get(uploadRootPath, filename);
            Files.write(filePath, file.getBytes());
            // Kiểm tra định dạng của tệp
            boolean isImage = Files.probeContentType(filePath).startsWith("image");
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }
}
