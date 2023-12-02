package com.StrangerBookstore.service;

import com.StrangerBookstore.model.Orders;
import com.StrangerBookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }
}
