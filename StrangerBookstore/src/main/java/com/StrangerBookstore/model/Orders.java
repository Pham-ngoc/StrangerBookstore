package com.StrangerBookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Orders extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "status_id", referencedColumnName = "statusId", nullable = true)
    private StatusOrders statusOrders;

    private String paymentMethod;

    private double totalAmount;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Orders order = (Orders) o;
        return orderId == order.orderId
                && Objects.equals(customer, order.customer)
                && Objects.equals(statusOrders, order.statusOrders)
                && Objects.equals(paymentMethod, order.paymentMethod)
                && Objects.equals(totalAmount, order.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, customer, statusOrders, paymentMethod, totalAmount);
    }
}
