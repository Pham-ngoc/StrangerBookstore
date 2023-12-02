package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.StatusOrders;
import com.StrangerBookstore.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    StatusOrderRepository statusOrderRepository;

    @GetMapping("/myOrder")
    public String viewOrder(Model model){
        List<StatusOrders> listStatus = statusOrderRepository.findAll();
        model.addAttribute("listStatus", listStatus);
        return "User-view/my-order.html";
    }

    @GetMapping("/getOrderDetail")
    public String orderDetail(Model model){
        List<StatusOrders> listStatus = statusOrderRepository.findAll();
        model.addAttribute("listStatus", listStatus);
        return "User-view/my-order-detail.html";
    }
}
