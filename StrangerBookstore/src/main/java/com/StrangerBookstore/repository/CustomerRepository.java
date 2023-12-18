package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.email like ?1")
    Customer readByEmail(String email);

    @Query("select c from Customer c where c.customerId = ?1")
    Customer findByCustomerId(int customerId);

    @Query("SELECT new com.StrangerBookstore.model.Report(CONCAT(FUNCTION('YEAR', c.createAt), '-', FUNCTION('MONTH', c.createAt)), COUNT(c)) " +
            "FROM Customer c " +
            "GROUP BY CONCAT(FUNCTION('YEAR', c.createAt), '-', FUNCTION('MONTH', c.createAt)) " +
            "ORDER BY CONCAT(FUNCTION('YEAR', c.createAt), '-', FUNCTION('MONTH', c.createAt)) ASC")
    List<Report> getCustomerReports();
}
