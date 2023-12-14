package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.OrderDetail;
import com.StrangerBookstore.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import com.StrangerBookstore.service.orderdetailService;
import com.StrangerBookstore.repository.orderdetailreponsive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    orderdetailService orderdetailService;
    @Autowired
    orderdetailreponsive orderdetailreponsive;

    @GetMapping("/orderDetail")
    public List<?> categories(){
         return orderdetailService.findAll();
    }
}
