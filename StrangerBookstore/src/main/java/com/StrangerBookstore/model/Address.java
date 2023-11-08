package com.StrangerBookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int addressId;
    private String recipientFullName;
    private String recipientPhoneNumber;
    private String addressDetail;
    private String addressType;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId", nullable = true)
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return addressId == address.addressId
                && Objects.equals(recipientFullName, address.recipientFullName)
                && Objects.equals(recipientPhoneNumber, address.recipientPhoneNumber)
                && Objects.equals(addressDetail, address.addressDetail)
                && Objects.equals(addressType, address.addressType)
                && Objects.equals(customer, address.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addressId, recipientFullName, recipientPhoneNumber, addressDetail, addressType, customer);
    }

}
