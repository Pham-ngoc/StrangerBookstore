package com.StrangerBookstore.controller.AdminController;

import ch.qos.logback.core.model.Model;

import com.StrangerBookstore.model.ShipInfor;

import com.StrangerBookstore.repository.ShipInforRepository;
import com.StrangerBookstore.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminShipInforController {


    @Autowired
    ShipService shipService;

    @Autowired
    ShipInforRepository shipInforRepository;
    @GetMapping("/shipInformation")
    public ResponseEntity<List<ShipInfor>> ship(Model model){
        return ResponseEntity.ok(shipInforRepository.shipFindAll());
    }

//    @PutMapping("/shipInformation/{id}")
//    public ResponseEntity<ShipInfor> update(@PathVariable("id") Integer id, @RequestBody ShipInfor ship) {
//        ShipInfor updateShip = shipService.update(id, ship);
//        if (updateShip != null) {
//            return ResponseEntity.ok(updateShip);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PutMapping("/shipInformation/{id}")
    public ResponseEntity<ShipInfor> updateShip(@PathVariable("id") Integer id, @RequestBody ShipInfor ship) {
        shipService.updateShip(id, ship.getStatus(), ship.getNote());
        // Trả về đối tượng đã được cập nhật nếu cần
        return ResponseEntity.ok(ship);
    }

}
