package com.StrangerBookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Picture extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int pictureId;

    @NotBlank(message = "Product must not be blank")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="product_id", referencedColumnName = "productId", nullable = true)
    private Product product;

    @NotBlank(message = "Picture File must not be blank")
    private String pictureFile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Picture picture = (Picture) o;
        return pictureId == picture.pictureId
                && Objects.equals(product, picture.product)
                && Objects.equals(pictureFile, picture.pictureFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pictureId, product, pictureFile);
    }
}
