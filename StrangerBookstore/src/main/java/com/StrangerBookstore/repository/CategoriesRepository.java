package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
}