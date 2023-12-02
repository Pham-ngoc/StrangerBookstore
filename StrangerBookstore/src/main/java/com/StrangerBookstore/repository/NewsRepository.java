package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {
    @Query("SELECT n FROM News n")
    List<News> newFindAll();
}
