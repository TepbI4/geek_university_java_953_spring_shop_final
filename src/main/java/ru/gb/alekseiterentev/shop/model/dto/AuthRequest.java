package ru.gb.alekseiterentev.shop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthRequest {
    private String username;
    private String password;
}
