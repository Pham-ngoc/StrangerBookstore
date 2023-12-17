package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.ProductReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewsReposity extends JpaRepository<ProductReviews,Integer> {
}
