package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.Cart;
import com.StrangerBookstore.model.ContactUs;
import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.repository.CartRepository;
import com.StrangerBookstore.repository.ContactRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AboutController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    HttpSession session;

    @RequestMapping ("/about")
    public String about(Model model){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        if(customer != null){
            List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
            session.setAttribute("itemInCart", cartProduct.size());
        }
        return "User-view/about-us.html";
    }

    @RequestMapping("/contact")
    public String contact(Model model){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        if(customer != null){
            List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
            session.setAttribute("itemInCart", cartProduct.size());
        }
        model.addAttribute("contact", new ContactUs());
        return "User-view/contact.html";
    }

    @PostMapping(value = "/saveMsg")
    public String saveMsg(@Valid @ModelAttribute("contact") ContactUs contactUs, BindingResult bindingResult, RedirectAttributes ra){
        if(bindingResult.hasErrors()){
            return "User-view/contact.html";
        } else {
            ContactUs contact = new ContactUs();
            contact.setFullName(contactUs.getFullName());
            contact.setPhoneNumber(contactUs.getPhoneNumber());
            contact.setEmail(contactUs.getEmail());
            contact.setSubject(contactUs.getSubject());
            contact.setMessage(contactUs.getMessage());
            contact.setStatus("Enable");
            contactRepository.save(contact);
            ra.addFlashAttribute("message", "Contact sent successfully!");
        }
        return "redirect:/contact";
    }
}
