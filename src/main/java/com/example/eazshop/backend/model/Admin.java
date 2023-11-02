package com.example.eazshop.backend.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
    private String adminId;
    private String password;
}
