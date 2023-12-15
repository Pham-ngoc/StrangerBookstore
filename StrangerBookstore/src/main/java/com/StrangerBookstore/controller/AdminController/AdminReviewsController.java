package com.StrangerBookstore.controller.AdminController;


import com.StrangerBookstore.model.ProductReviews;
import com.StrangerBookstore.repository.ProductReviewsReposity;
import com.StrangerBookstore.service.ProductReviewsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminReviewsController {
    @Autowired
    ProductReviewsService reviewsService;

    @Autowired
    ProductReviewsReposity productReviewsReposity;

    @GetMapping("/reviews")
    public ResponseEntity<List<ProductReviews>> reviews(Model model){
        return ResponseEntity.ok(productReviewsReposity.findAll());
    }


}