package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.StatusOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOrders, Integer> {
    @Query("select s from StatusOrders s where s.statusName like ?1")
    StatusOrders findStatusByName(String statusName);
}
