package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.News;
import com.StrangerBookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT n FROM Order n")
    List<Order> newFindAll();
}
