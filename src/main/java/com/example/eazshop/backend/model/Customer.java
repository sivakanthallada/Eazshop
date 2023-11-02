package com.example.eazshop.backend.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor // No need to mention getter and setter, Lombok will do by this
@NoArgsConstructor // No need to mention getter and setter, Lombok will do by this
@Component
@Getter
@Setter
public class Customer {

    private String customerId; // Set customerId as a primary key

    private String customerName;

    private long contact;
    private String mailId;
    private String password;

    public Customer(String customerName, long contact, String mailId, String password) {
        this.customerName = customerName;
        this.contact = contact;
        this.mailId = mailId;
        this.password = password;
    }
}
