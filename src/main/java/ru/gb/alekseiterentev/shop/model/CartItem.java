package ru.gb.alekseiterentev.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {

    private Product product;
    private int quantity;
    private int total;
}
