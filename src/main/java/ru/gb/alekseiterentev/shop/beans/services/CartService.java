package ru.gb.alekseiterentev.shop.beans.services;

import ru.gb.alekseiterentev.shop.model.CartItem;
import ru.gb.alekseiterentev.shop.model.Product;

import java.util.List;

public interface CartService {
    void addToCart(Product product);
    List<CartItem> getCartItems();
}
