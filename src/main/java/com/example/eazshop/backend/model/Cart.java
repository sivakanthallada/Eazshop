package com.example.eazshop.backend.model;


import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Cart {
    private String productCode;
    private String customerId;
    private String image;
    private String productName;
    private long price;
}
