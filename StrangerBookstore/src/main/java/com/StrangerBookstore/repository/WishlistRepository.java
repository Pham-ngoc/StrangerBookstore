package com.StrangerBookstore.repository;


import com.StrangerBookstore.model.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    @Query("select w from Wishlist w where w.customer.customerId = ?1")
    Page<Wishlist> findAllByCustomerID(Pageable pageable, int customerId);

    @Query("select w from Wishlist w where w.customer.customerId = ?1 and w.products.productId = ?2")
    Wishlist findByUserAndProduct(int customerId, int productId);
}
