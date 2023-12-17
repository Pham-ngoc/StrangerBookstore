package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("select a from Address a where a.customer.customerId = ?1")
    Page<Address> findByCustomerId(int customerId, Pageable pageable);

    @Query("select a from Address a where a.addressId = ?1")
    Address findByAddressId(int addressId);

    @Query("select a from Address a where a.customer.customerId = ?1")
    List<Address> findAllByCustomerId(int customerId);

    @Query("select a from Address a where a.addressDetail like ?1")
    Address findByAddressDetail(String addressDetail);

}
