package com.StrangerBookstore.service;

import com.StrangerBookstore.model.ProductReviews;
import com.StrangerBookstore.repository.ProductReviewsReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewsService {
    @Autowired
    ProductReviewsReposity productReviewsReposity;

    public List<ProductReviews> findAll() {

        return productReviewsReposity.findAll();
    }

    public Optional<ProductReviews> findbyId(Integer id){
        return productReviewsReposity.findById(id);
    }


}