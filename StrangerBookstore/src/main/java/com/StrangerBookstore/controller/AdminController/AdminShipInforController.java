package com.StrangerBookstore.controller.AdminController;

import ch.qos.logback.core.model.Model;

import com.StrangerBookstore.model.ShipInfor;

import com.StrangerBookstore.repository.ShipReponsitory;
import com.StrangerBookstore.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminShipInforController {


    @Autowired
    ShipService service;

    @Autowired
    ShipReponsitory shipReponsitory;
    @GetMapping("/shipInformation")
    public ResponseEntity<List<ShipInfor>> ship(Model model){
        return ResponseEntity.ok(shipReponsitory.shipFindAll());
    }
}
