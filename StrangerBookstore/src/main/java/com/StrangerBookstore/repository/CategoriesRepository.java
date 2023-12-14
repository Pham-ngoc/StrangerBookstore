package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
   // @Query(value = "SELECT a from strangerbookstore.categories a  where a.category_name like concat('%',:query,'%') ", nativeQuery = true)
    List<Categories> findByCategoryName(String query);
}