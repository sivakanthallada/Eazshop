package com.example.eazshop.backend.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coupon {

    private String couponCode; // Primary key for Coupon Table

    private int discountPercentage;

    private Date validTill;

}
