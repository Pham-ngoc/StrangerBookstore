package com.StrangerBookstore.service;

import com.StrangerBookstore.model.*;
import com.StrangerBookstore.repository.CustomerRepository;
import com.StrangerBookstore.repository.OrderRepository;
import com.StrangerBookstore.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    StatusOrderRepository statusOrderRepository;

    public List<Orders> findAll() {

        return orderRepository.findAll();
    }

    public Orders update(Integer id, Orders order) {
        Optional<Orders> existingOrdersOptional = orderRepository.findById(id);
        if (existingOrdersOptional.isPresent()) {
            Orders existingOrders = existingOrdersOptional.get();

            // Cập nhật các trường thông tin khác
            //customer
            if (!existingOrders.getCustomer().equals(order.getCustomer())) {
                Optional<Customer> customerOptional = customerRepository.findById(order.getCustomer().getCustomerId());
                if (customerOptional.isPresent()) {
                    existingOrders.setCustomer(customerOptional.get());
                } else {
                    return null;
                }
            };
            //status
            if (!existingOrders.getStatusOrders().equals(order.getStatusOrders())) {
                Optional<StatusOrders> statusOrdersrOptional = statusOrderRepository.findById(order.getStatusOrders().getStatusId());
                if (statusOrdersrOptional.isPresent()) {
                    existingOrders.setStatusOrders(statusOrdersrOptional.get());
                } else {
                    return null;
                }
            }
            existingOrders.setPaymentMethod(order.getPaymentMethod());
            existingOrders.setTotalAmount(order.getTotalAmount());
            return orderRepository.save(existingOrders);
        } else {
            return null;
        }
    }


}
