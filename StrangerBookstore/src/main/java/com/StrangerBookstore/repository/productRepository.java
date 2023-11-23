package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product, Integer> {
}
