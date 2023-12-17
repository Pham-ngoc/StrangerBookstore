package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Orders;
import com.StrangerBookstore.model.StatusOrders;
import com.StrangerBookstore.repository.OrdersRepository;
import com.StrangerBookstore.repository.StatusOrderRepository;
import com.StrangerBookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    OrdersRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    StatusOrderRepository statusOrderRepository;

    @GetMapping("/status")
    public ResponseEntity<List<StatusOrders>> status(Model model){
        return ResponseEntity.ok(statusOrderRepository.findAll());
    }

    @GetMapping("/order")
    public ResponseEntity<List<Orders>> order(Model model){
        return ResponseEntity.ok(orderRepository.orderFindAll());
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Orders> update(@PathVariable("id") Integer id, @RequestBody Orders order) {
        Orders updateOrder = orderService.update(id, order);
        if (updateOrder != null) {
            return ResponseEntity.ok(updateOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
