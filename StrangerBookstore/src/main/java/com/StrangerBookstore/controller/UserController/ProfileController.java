package com.StrangerBookstore.controller.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    @RequestMapping("/personal")
    public String personal(Model model){
        return "User-view/profile/personal.html";
    }

    @RequestMapping("/changePass")
    public String changepass(Model model){
        return "User-view/profile/changePass.html";
    }

    @RequestMapping("/address")
    public String address(Model model){
        return "User-view/profile/address.html";
    }

    @RequestMapping("/address/add")
    public String add_address(Model model){
        return "User-view/profile/edit_add_address.html";
    }

    @RequestMapping("/wishlist")
    public String wishlist(Model model){
        return "User-view/profile/wishlist.html";
    }

}
