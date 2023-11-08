package com.StrangerBookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Categories extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int categoryId;

    @NotBlank(message = "Category Name must not be blank")
    private String categoryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Categories categories = (Categories) o;
        return categoryId == categories.categoryId
                && Objects.equals(categoryName, categories.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryId, categoryName);
    }
}
