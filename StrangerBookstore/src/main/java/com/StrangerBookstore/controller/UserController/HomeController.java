package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.Cart;
import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.CartRepository;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @GetMapping("/home")
    public String home(Model model, Authentication authentication, HttpSession session){
        List<Products> listProduct = productRepository.findTop6Products();
        model.addAttribute("listProduct", listProduct);
        if(authentication != null) {
            Customer customer = customerRepository.readByEmail(authentication.getName());
            List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
            System.out.println(customer);
            if (customer.getCustomerId() > 0) {
                model.addAttribute("userName", customer.getCustomerName());
                model.addAttribute("roles", authentication.getAuthorities().toString());
                System.out.println(authentication.getAuthorities().toString());
                session.setAttribute("loggingCustomer", customer);
                session.setAttribute("itemInCart", cartProduct.size());
            }
            return "User-view/home.html";
        }
        return "User-view/home.html";
    }
}
