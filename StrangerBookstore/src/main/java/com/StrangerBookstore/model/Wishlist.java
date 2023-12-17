package com.StrangerBookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int wishlistId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_id",referencedColumnName = "customerId",nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="product_id", referencedColumnName = "productId", nullable = true)
    private Products products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Wishlist wishlist = (Wishlist) o;
        return wishlistId == wishlist.wishlistId
                && Objects.equals(customer, wishlist.customer)
                && Objects.equals(products, wishlist.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wishlistId, customer, products);
    }

}
