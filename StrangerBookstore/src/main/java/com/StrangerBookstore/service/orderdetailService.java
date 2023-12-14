package com.StrangerBookstore.service;

import com.StrangerBookstore.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.StrangerBookstore.repository.orderdetailreponsive;

import java.util.List;

@Service
public class orderdetailService {
    @Autowired
    orderdetailreponsive orderdetailreponsive;

    public List<OrderDetail> findAll() {
        return orderdetailreponsive.getAllOrder();
    }
}
