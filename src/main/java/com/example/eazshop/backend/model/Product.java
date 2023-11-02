package com.example.eazshop.backend.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Clob;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    private String productCode; // Primary key for Product Table

    private String productName;

    private String category;

    private String model;

    private String manufacturer;

    private Date manufactureDate;

    private Date expiryDate;

    private long price;
    private String productDesc;
    private String image;
}
