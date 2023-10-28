package com.StrangerBookstore.controller.AdminController;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminOrderDetailController {
    @GetMapping("/orderDetail")
    public String orderDetail(Model model){
        return "Admin-view/admin-orderdetail.html";
    }
}
