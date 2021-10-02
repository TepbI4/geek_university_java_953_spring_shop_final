package ru.gb.alekseiterentev.shop.beans.services;

import ru.gb.alekseiterentev.shop.utils.Cart;

public interface CartService {
    Cart getCartForCurrentUser();
    void addItem(Long productId);
    void removeItem(Long productId);
    void decreaseItemQuantity(Long productId);
    void clearCart();
}
