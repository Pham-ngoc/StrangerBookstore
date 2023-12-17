package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.ProductReviews;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminReviewsController {

    @Autowired
    ReviewsRepository reviewsRepository;
    @GetMapping("/reviews")
    public ResponseEntity<List<ProductReviews>> reviews(Model model){
        return ResponseEntity.ok(reviewsRepository.findAll());
    }

    @GetMapping("/reviews/{reviewsId}")
    public ResponseEntity<ProductReviews> getByProductId(@PathVariable("reviewsId") int reviewsId){
        return ResponseEntity.ok(reviewsRepository.findByReviewsId(reviewsId));
    }
}
