package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Orders;
import com.StrangerBookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminOrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/order")
    public ResponseEntity<List<Orders>> order(Model model){
        return ResponseEntity.ok(orderRepository.orderFindAll());
    }


}
