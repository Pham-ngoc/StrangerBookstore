package com.StrangerBookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int cartId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="customer_id", referencedColumnName = "customerId", nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="product_id", referencedColumnName = "productId", nullable = true)
    private Products product;

    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cart cart = (Cart) o;
        return cartId == cart.cartId
                && Objects.equals(customer, cart.customer)
                && Objects.equals(product, cart.product)
                && Objects.equals(quantity, cart.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cartId, customer, product, quantity);
    }

}
