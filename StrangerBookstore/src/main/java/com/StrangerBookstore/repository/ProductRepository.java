package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    @Query("select p from Products p")
    Page<Products> findAllProduct(Pageable pageable);

    @Query("select p from Products p where p.productId = ?1")
    Products findByProductId(int productId);
}
