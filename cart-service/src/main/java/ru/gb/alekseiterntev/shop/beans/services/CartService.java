package ru.gb.alekseiterntev.shop.beans.services;

import ru.gb.alekseiterntev.shop.beans.utils.Cart;

public interface CartService {
    Cart getCartForCurrentUser(String cartId);
    void updateCart(String cartId, Cart cart);
    void addItem(String cartId, Long productId);
    void removeItem(String cartId, Long productId);
    void decreaseItemQuantity(String cartId, Long productId);
    void clearCart(String cartId);
    boolean isCartExists(String cartId);
}
