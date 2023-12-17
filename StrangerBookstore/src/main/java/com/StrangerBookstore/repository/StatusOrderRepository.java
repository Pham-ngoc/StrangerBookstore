package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.StatusOrders;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> main
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOrders, Integer> {
<<<<<<< HEAD
=======

    @Query("select s from StatusOrders s where s.statusName like ?1")
    StatusOrders findStatusByName(String statusName);
>>>>>>> main
}
