package com.StrangerBookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class ShipInfor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int shipId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Address.class)
    @JoinColumn(name="address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Order.class)
    @JoinColumn(name="order_id", referencedColumnName = "orderId", nullable = true)
    private Order order;

    private String status;

    private String note;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ShipInfor shipInfor = (ShipInfor) o;
        return shipId == shipInfor.shipId
                && Objects.equals(address, shipInfor.address)
                && Objects.equals(order, shipInfor.order)
                && Objects.equals(status, shipInfor.status)
                && Objects.equals(note, shipInfor.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shipId, address, order, status, note);
    }
}
