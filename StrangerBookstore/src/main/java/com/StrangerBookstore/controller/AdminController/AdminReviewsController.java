package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Product;
import com.StrangerBookstore.model.ProductReviews;
import com.StrangerBookstore.repository.ProductReviewsReposity;
import com.StrangerBookstore.service.ProductReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminReviewsController {
    @Autowired
    ProductReviewsService service;

    @Autowired
    ProductReviewsReposity productReviewsReposity;

    @GetMapping("/reviews")
    public ResponseEntity<List<ProductReviews>> categories(Model model){
        return ResponseEntity.ok(productReviewsReposity.findAll());
    }


    @GetMapping("/reviews/{id}")
    public ResponseEntity<ProductReviews> getreviews(@PathVariable Integer id) {
        Optional<ProductReviews> reviewsOptional = service.findbyId(id);
        return reviewsOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/reviews")
    public ResponseEntity<ProductReviews> create(@RequestBody ProductReviews reviews) {
        ProductReviews reviewspicture = service.create(reviews);
        return ResponseEntity.ok(reviewspicture);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<ProductReviews> updatereviews(@PathVariable("id") Integer id, @RequestBody ProductReviews reviews) {
        if(service. findbyId (id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.update(id, reviews);
        }
        return ResponseEntity.ok(reviews);
    }


    @DeleteMapping("/reviews/{id}")
    public void deletereviews(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}