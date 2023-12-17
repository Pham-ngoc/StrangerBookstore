package com.StrangerBookstore.service;

import com.StrangerBookstore.model.OrderDetail;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.OrderDetailRepository;
import com.StrangerBookstore.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    private static Pageable getPageable(int pageNumber){
        int pageShow = 5;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageShow, Sort.by("orderDetailsId").descending());
        return pageable;
    }

    public Page<OrderDetail> findByOrderId(int pageNumber, int orderId){
        Pageable pageable = getPageable(pageNumber);
        Page<OrderDetail> pageOrderDetail = orderDetailRepository.findByOrderId(pageable, orderId);
        return pageOrderDetail;
    }

    public Page<OrderDetail> findByOrderIdAndStatus(int pageNumber, int orderId, int statusId){
        Pageable pageable = getPageable(pageNumber);
        Page<OrderDetail> pageOrderDetail = orderDetailRepository.findByOrderIdAndStatus(pageable, orderId, statusId);
        return pageOrderDetail;
    }
}
