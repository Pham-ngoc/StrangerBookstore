package com.StrangerBookstore.service;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.model.Roles;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.repository.RoleRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

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
        System.out.println(roles);
        customer.setRoles(roles);
        customer.setStatus("Open");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setCreateAt(LocalDateTime.now());
        customer.setCreateBy("anonymousUser");
        customer.setPicture("account1.jpg");
        Customer createCustomer = customerRepository.save(customer);
        if (createCustomer != null && createCustomer.getCustomerId() >= 0) {
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

    public boolean updatePassword(Customer customer){
        boolean isUpdated = false;
        long currentTimeMillis = System.currentTimeMillis();
        int newPass = (int) (currentTimeMillis % 1000000);
        customer.setPassword(passwordEncoder.encode(String.valueOf(newPass)));
        customer = customerRepository.save(customer);
        customer.setPasswordConfirm(String.valueOf(newPass));
        if(customer != null && customer.getCustomerId() >=0){
            isUpdated = true;
        }
        return isUpdated;
    }

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

            boolean isImage = Files.probeContentType(filePath).startsWith("image");
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }
}
