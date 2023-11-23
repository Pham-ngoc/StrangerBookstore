package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface pictureRepository extends JpaRepository<Picture,Integer> {
}
