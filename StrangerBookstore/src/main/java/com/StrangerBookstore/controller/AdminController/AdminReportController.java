package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.ReportOrder;
import com.StrangerBookstore.repository.OrdersRepository;
import org.openqa.selenium.remote.Augmentable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminReportController {

    @Autowired
    OrdersRepository ordersRepository;

    @GetMapping("/report")
    public ResponseEntity<List<ReportOrder>> report(Model model){
        return ResponseEntity.ok(ordersRepository.getOrderReport());
    }
}
