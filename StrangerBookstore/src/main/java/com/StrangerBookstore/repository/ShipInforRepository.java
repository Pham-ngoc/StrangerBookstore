package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Address;
import com.StrangerBookstore.model.ShipInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipInforRepository extends JpaRepository<ShipInfor, Integer> {

    @Query("select s from ShipInfor s where s.order.orderId = ?1")
    ShipInfor findByOrderId (int orderId);

    @Query("select s.address.addressId from ShipInfor s where s.order.orderId = ?1")
    int findAddressByOrderId (int orderId);

    @Query("SELECT o FROM ShipInfor o")
    List<ShipInfor> shipFindAll();

    @Modifying
    @Query("UPDATE ShipInfor s SET s.status = :status, s.note = :note WHERE s.shipId = :shipId")
    void updateShip(@Param("shipId") int shipId, @Param("status") String status, @Param("note") String note);

}
