package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.OrderDetail;
import com.StrangerBookstore.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("select od from OrderDetail od where od.orders.orderId = ?1")
    Page<OrderDetail> findByOrderId(Pageable pageable, int orderId);

    @Query("select od from OrderDetail od where od.orders.orderId = ?1 and od.orders.statusOrders.statusId = ?2")
    Page<OrderDetail> findByOrderIdAndStatus(Pageable pageable, int orderId, int statusId);

    @Query("SELECT od.orders.orderId FROM OrderDetail od where od.orderDetailsId = ?1 ")
    int findOrdersByOrderDetailId(int orderDetailsId);

    @Query("SELECT od FROM OrderDetail od where od.orderDetailsId = ?1 ")
    OrderDetail findAllByOrderDetailId(int orderDetailsId);
}
