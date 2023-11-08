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
public class ContactUs extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int contactId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String subject;
    private String message;
    private String status;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ContactUs contactUs = (ContactUs) o;
        return contactId == contactUs.contactId
                && Objects.equals(fullName, contactUs.fullName)
                && Objects.equals(phoneNumber, contactUs.phoneNumber)
                && Objects.equals(email, contactUs.email)
                && Objects.equals(subject, contactUs.subject)
                && Objects.equals(message, contactUs.message)
                && Objects.equals(status, contactUs.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contactId, fullName, phoneNumber, email, subject, message, status);
    }
}
