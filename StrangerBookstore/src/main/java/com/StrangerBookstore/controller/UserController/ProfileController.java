package com.StrangerBookstore.controller.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("/personal")
    public String personal(Model model){
        return "User-view/profile-personal.html";
    }

    @RequestMapping("/changePass")
    public String changepass(Model model){
        return "User-view/profile-changepassword.html";
    }

    @RequestMapping("/address")
    public String address(Model model){
        return "User-view/profile-address.html";
    }

    @RequestMapping("/addressEdit")
    public String add_address(Model model){
        return "User-view/profile-editAddress.html";
    }

    @RequestMapping("/wishlist")
    public String wishlist(Model model){
        return "User-view/profile-wishlist.html";
    }

}
