package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    CustomerRepository customerRepository;
    @GetMapping("/home")
    public String home(Model model, Authentication authentication, HttpSession session){
        if(authentication == null){
            return "User-view/home.html";
        }
        Customer customer = customerRepository.readByEmail(authentication.getName());
        System.out.println(customer);
        if(customer != null && customer.getCustomerId()>0){
            model.addAttribute("userName", customer.getCustomerName() );
            model.addAttribute("roles", authentication.getAuthorities().toString());
            System.out.println(authentication.getAuthorities().toString());
            session.setAttribute("loggingCustomer", customer);
        }
        return "User-view/home.html";
    }
}
