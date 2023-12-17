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
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int addressId;

    @NotBlank(message = "Recipient Name must not be blank")
    private String recipientFullName;

    @NotBlank(message = "Recipient Phone Number must not be blank")
    private String recipientPhoneNumber;

    @NotBlank(message = "Address Detail must not be blank")
    private String addressDetail;

    @NotBlank(message = "Address Type must not be blank")
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
