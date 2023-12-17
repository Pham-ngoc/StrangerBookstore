package com.StrangerBookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class OrderDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int orderDetailId;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "order_id", referencedColumnName = "orderId", nullable = true)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "product_id", referencedColumnName = "productId", nullable = true)
    private Product product;

    private int quantity;

    private double amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderDetail orderDetail = (OrderDetail) o;
        return orderDetailId == orderDetail.orderDetailId
                && Objects.equals(order, orderDetail.order)
                && Objects.equals(product, orderDetail.product)
                && Objects.equals(quantity, orderDetail.quantity)
                && Objects.equals(amount, orderDetail.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderDetailId, order, product, quantity, amount);
    }
}
