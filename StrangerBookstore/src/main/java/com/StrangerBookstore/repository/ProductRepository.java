package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    @Query("select p from Products p")
    Page<Products> findAllProduct(Pageable pageable);

    @Query("select p from Products p where p.productId = ?1")
    Products findByProductId(int productId);

    @Query("select p from Products p order by rand() limit 6")
    List<Products> findTop6Products();

    @Query("select p from Products p where p.categories.categoryId = ?1")
    Page<Products> fillAllProductByCateID(Pageable pageable, int cateId);

    @Query("select p from Products p where p.productName like %:searchName% and p.categories.categoryId = COALESCE(:categoryId, p.categories.categoryId)")
    Page<Products> fillProductByNameAndCateID(Pageable pageable, @Param("searchName") String searchName, @Param("categoryId") String categoryId);

    @Query("SELECT p FROM Products p WHERE p.price BETWEEN ?1 AND ?2")
    Page<Products> findProductsByPrice(double minPrice, double maxPrice, Pageable pageable);


}
