package com.StrangerBookstore.controller.UserController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

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
}
