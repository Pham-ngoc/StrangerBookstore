package com.StrangerBookstore.repository;

import org.springframework.data.jpa.repository.Modifying;
import com.StrangerBookstore.model.ShipInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipReponsitory extends JpaRepository<ShipInfor,Integer>{
    @Query("SELECT o FROM ShipInfor o")
    List<ShipInfor> shipFindAll();
    @Modifying
    @Query("UPDATE ShipInfor s SET s.status = :status, s.note = :note WHERE s.shipId = :shipId")
    void updateShip(@Param("shipId") int shipId, @Param("status") String status, @Param("note") String note);
}
