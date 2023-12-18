package com.StrangerBookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Entity
@Setter
@Getter
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int customerId;

    @NotBlank(message = "Customer Name must not be blank")
    private String customerName;

    @NotBlank(message = "Password must not be blank")
    private String password;

    @NotBlank(message = "Password Confirm must not be blank")
    @Transient
    private String passwordConfirm;


    @NotBlank(message = "Phone Number must not be blank")
    private String phoneNumber;


    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Email Confirm must not be blank")
    @Transient
    private String emailConfirm;


    @NotBlank(message = "Status must not be blank")
    private String status;

    @NotBlank(message = "Picture must not be blank")
    private String picture;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name="role_id", referencedColumnName = "roleId", nullable = true)
    private Roles roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId
                && Objects.equals(customerName, customer.customerName)
                && Objects.equals(phoneNumber, customer.phoneNumber)
                && Objects.equals(email, customer.email)
                && Objects.equals(emailConfirm, customer.emailConfirm)
                && Objects.equals(password, customer.password)
                && Objects.equals(passwordConfirm, customer.passwordConfirm)
                && Objects.equals(status, customer.status)
                && Objects.equals(picture, customer.picture)
                && Objects.equals(roles, customer.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerId, customerName, phoneNumber, email, emailConfirm, password, passwordConfirm, status, picture, roles);
    }

}
