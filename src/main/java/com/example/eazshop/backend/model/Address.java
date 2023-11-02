package com.example.eazshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component // To connect or use it as a component in any other class
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    private String addressId;

    private String customerId;

    private int zipCode;

    private String street; // Set collection of zipCode and street as a primary key

    private String city;

    private String state;

    private String country;

    public Address(int zipCode, String street, String city, String state, String country) {
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
