package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.ShipInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipInforRepository extends JpaRepository<ShipInfor, Integer> {

    @Query("select s from ShipInfor s where s.orders.orderId = ?1")
    ShipInfor findByOrderId (int orderId);
}
