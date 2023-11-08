package com.StrangerBookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class StatusOrders extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int statusId;

    private String statusName;

    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StatusOrders statusOrders = (StatusOrders) o;
        return statusId == statusOrders.statusId
                && Objects.equals(statusName, statusOrders.statusName)
                && Objects.equals(note, statusOrders.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), statusId, statusName, note);
    }
}
