package com.StrangerBookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class ProductReviews extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int reviewsId;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "product_id", referencedColumnName = "productId", nullable = true)
    private Product product;

    private String reviewContent;

    private int starForProduct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductReviews productReviews = (ProductReviews) o;
        return reviewsId == productReviews.reviewsId
                && Objects.equals(customer, productReviews.customer)
                && Objects.equals(product, productReviews.product)
                && Objects.equals(reviewContent, productReviews.reviewContent)
                && Objects.equals(starForProduct, productReviews.starForProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reviewsId, customer, product, reviewContent, starForProduct);
    }
}
