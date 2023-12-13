package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface contactReponsitory extends JpaRepository<ContactUs,Integer> {
}
