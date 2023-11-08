package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
    Customer readByEmail(String email);
}
