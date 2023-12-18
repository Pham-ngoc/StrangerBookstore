package com.StrangerBookstore.controller.AdminController;


import com.StrangerBookstore.model.OrderDetail;
import com.StrangerBookstore.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminOrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("/orderDetail")
    public List<OrderDetail> getAll(){
        return orderDetailService.findAll();
    }

}