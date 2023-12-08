package com.StrangerBookstore.repository;


import com.StrangerBookstore.model.News;
import com.StrangerBookstore.model.ShipInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipReponsitory extends JpaRepository<ShipInfor,Integer>{
    @Query("SELECT o FROM ShipInfor o")
    List<ShipInfor> shipFindAll();
}
