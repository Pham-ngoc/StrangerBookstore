package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Products, Integer> {
    @Query("SELECT o FROM Products o")
    List<Products> productFindAll();

}
