package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c where c.customer.customerId = ?1 and c.product.productId = ?2")
    Cart findByUserAndProduct(int customerId, int productId);

    @Query("select c from Cart c where c.customer.customerId = ?1")
    List<Cart> findAllByCustomerId(int customerId);

    @Query("select c from Cart c where c.product.productId = ?1")
    Cart findCartItemByProductId(int productId);

    @Query("select count(c.cartId) from Cart c where c.customer.customerId = ?1")
    int getSize(int customerId);
    int countByCartId(int cartId);

    @Query("SELECT COALESCE(SUM(c.quantity * c.product.price), 0.0) FROM Cart c WHERE c.customer.customerId = ?1")
    double getAmount(int customerId);

    @Query("delete from Cart c where c.customer.customerId = ?1")
    List<Cart> deleteByCustomerId(int customerId);

}
