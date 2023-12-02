package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.StatusOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOrders, Integer> {
}
