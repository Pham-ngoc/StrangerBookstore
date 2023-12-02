package com.StrangerBookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Products extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int productId;

    @NotBlank(message = "Product Name must not be blank")
    private String productName;

    @NotBlank(message = "Author must not be blank")
    private String author;

    @NotBlank(message = "Publisher must not be blank")
    private String publisher; //Nhà xuất bản

    @NotBlank(message = "Language must not be blank")
    private String language;

    @NotBlank(message = "Condition must not be blank")
    private String condition; //Tình trạng

    @NotBlank(message = "Quantity In Stock must not be blank")
    private int quantityInStock;

    @NotBlank(message = "Quantity In Stock must not be blank")
    private String isbn;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotBlank(message = "Description must not be blank")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="category_id", referencedColumnName = "categoryId", nullable = true)
    private Categories categories;

    @NotNull(message = "Product image number must not be blank")
    private String product_img;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Products product = (Products) o;
        return productId == product.productId
                && Objects.equals(productName, product.productName)
                && Objects.equals(author, product.author)
                && Objects.equals(publisher, product.publisher)
                && Objects.equals(language, product.language)
                && Objects.equals(condition, product.condition)
                && Objects.equals(quantityInStock, product.quantityInStock)
                && Objects.equals(isbn, product.isbn)
                && Objects.equals(price, product.price)
                && Objects.equals(description, product.description)
                && Objects.equals(product_img, product.product_img)
                && Objects.equals(categories, product.categories);

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productId, productName, author, publisher, language, condition, quantityInStock, isbn, price, description, product_img, categories);
    }

}