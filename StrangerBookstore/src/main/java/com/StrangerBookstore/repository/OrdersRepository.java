package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query("select o from Orders o where o.customer.customerId = ?1")
    List<Orders> findByCustomerId(int customerId);

    @Query("SELECT o FROM Orders o")
    List<Orders> orderFindAll();
}
