package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class SignUpController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;


    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("customer", new Customer());
        return "User-view/sign-up.html";
    }

    @PostMapping("/doSignup")
    public String doSignup(Model model, @Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, RedirectAttributes ra){
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "User-view/sign-up.html";
        } else {
            if (!customer.getEmail().equals(customer.getEmailConfirm())) {
                model.addAttribute("emailMismatch", "Email do not match");
                System.out.println("Error Email Confirm");
                return "User-view/sign-up.html";
            }
            if(!customer.getPassword().equals(customer.getPasswordConfirm())) {
                model.addAttribute("passwordMismatch", "Password do not match");
                System.out.println("Error Password Confirm");
                return "User-view/sign-up.html";
            }
        }
        Customer customerEmail = customerRepository.readByEmail(customer.getEmail());
        System.out.println(customerEmail);
        System.out.println("Before creating new customer");
        if(customerEmail == null){
            boolean isCreate = customerService.createNewCustomer(customer);
            System.out.println(isCreate);
            System.out.println("After creating new customer");
            if(isCreate) {
                return "redirect:/login?SignupSuccessfully";
            } else {
                model.addAttribute("message", "Can't Sign in");
                return "User-view/sign-up.html";
            }
        } else {
            model.addAttribute("emailMismatch", "Email already exists");
            return "User-view/sign-up.html";
        }
    }
}
