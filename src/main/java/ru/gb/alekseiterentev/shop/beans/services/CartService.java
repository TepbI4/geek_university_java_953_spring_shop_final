package ru.gb.alekseiterentev.shop.beans.services;

import ru.gb.alekseiterentev.shop.model.CartItem;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.utils.Cart;

import java.util.List;

public interface CartService {
    Cart getCartForCurrentUser();
    void addItem(Long productId);
    void removeItem(Long productId);
    void decreaseItemQuantity(Long productId);
    void clearCart();
}
