package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.ProductReviews;
import com.StrangerBookstore.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<ProductReviews, Integer> {
    @Query("select pr from ProductReviews pr where pr.reviewsId = ?1")
    ProductReviews findByReviewsId(int reviewsId);

    @Query("select pr from ProductReviews pr where pr.product.productId = ?1")
    List<ProductReviews> findByProductId(int productId);
}
