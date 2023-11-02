package com.example.eazshop.backend.model;



import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class Orders {

    private String orderId;

    // foreign key referencing to Customer class
    private String customerId;

    // foreign key referencing to Product class
    private String productCode;

    private double amount;

    // foreign key referencing to Coupon class
    private String couponCode;

    private int quantity;

    // @Column for generating the default current time stamp
    private Date orderDate;


    private double discountedAmount;

    private String addressId;
}
