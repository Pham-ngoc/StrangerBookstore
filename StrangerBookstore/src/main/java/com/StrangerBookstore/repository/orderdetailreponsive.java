package com.StrangerBookstore.repository;

import com.StrangerBookstore.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderdetailreponsive extends JpaRepository<OrderDetail,Integer> {

    @Query(value = "SELECT * FROM strangerbookstore.order_detail",nativeQuery = true)
    List<OrderDetail> getAllOrder();

}
