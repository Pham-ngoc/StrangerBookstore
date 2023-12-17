package com.StrangerBookstore.service;


import com.StrangerBookstore.model.ShipInfor;
import com.StrangerBookstore.repository.ShipInforRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipService {

    @Autowired
    ShipInforRepository shipInforRepository;

    public List<ShipInfor> findAll() {
        return shipInforRepository.shipFindAll();
    }

    @Transactional
    public void updateShip(int shipId, String status, String note) {
        shipInforRepository.updateShip(shipId, status, note);
    }
}
