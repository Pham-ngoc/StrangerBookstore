package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.service.CustomerService;
import com.StrangerBookstore.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    MailService mailService;

    @RequestMapping(value = "/login", method={ RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(Model model,
                                   @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("message", "Login Failed!");
            System.out.println(error);
        }
        return "User-view/login.html";
    }

    @GetMapping("/logout")
    public String displayLogOut(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout( request,response, auth);
        }
        return "redirect:/login?logout=true";
    }

    @GetMapping("/forget-password")
    public String forgetPassword(Model model){
        return "User-view/forget-password.html";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(Model model, @RequestParam("email") String email, RedirectAttributes red) throws MessagingException {
        Customer customer = customerRepository.readByEmail(email);
        if(customer == null){
//            String notFoundEmail = messageSource.getMessage("notFoundEmail",new Object[]{email}, LocaleContextHolder.getLocale());
            red.addFlashAttribute("notFoundEmail", "Sorry, we couldn't find the account with email: "+ email);
            return "redirect:/forget-password";
        }
        if(customerService.updatePassword(customer)){
            String emailFrom = "nganvps24932@fpt.edu.vn";
            String emailTo = email;
            mailService.sendEmail(customer, emailFrom,emailTo);
//            String emailUpdate = messageSource.getMessage("emailUpdate",null, LocaleContextHolder.getLocale());
            red.addFlashAttribute("emailUpdate", "Please check your email to get  your new Password");
            return "redirect:/forget-password";
        }
        return "redirect:/forget-password";
    }
}
