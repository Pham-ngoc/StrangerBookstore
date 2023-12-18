package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    List<Categories> findByCategoryName(String query);
}
