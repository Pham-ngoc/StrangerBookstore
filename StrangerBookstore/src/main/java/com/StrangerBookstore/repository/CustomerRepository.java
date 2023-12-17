package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.email like ?1")
    Customer readByEmail(String email);

    @Query("select c from Customer c where c.customerId = ?1")
    Customer findByCustomerId(int customerId);
}
