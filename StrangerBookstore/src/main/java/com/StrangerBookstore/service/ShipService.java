package com.StrangerBookstore.service;


import com.StrangerBookstore.model.ShipInfor;
import com.StrangerBookstore.repository.ShipReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipService {

    @Autowired
    ShipReponsitory shipReponsitory;

    public List<ShipInfor> findAll() {
        return shipReponsitory.shipFindAll();
    }

    @Transactional
    public void updateShip(int shipId, String status, String note) {
        shipReponsitory.updateShip(shipId, status, note);
    }
}
