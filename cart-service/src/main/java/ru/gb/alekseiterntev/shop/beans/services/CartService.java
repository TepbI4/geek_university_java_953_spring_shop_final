package ru.gb.alekseiterntev.shop.beans.services;

import ru.gb.alekseiterentev.shop.model.dto.ProductDto;
import ru.gb.alekseiterntev.shop.beans.utils.Cart;

public interface CartService {
    String getCartUuidFromSuffix(String suffix);
    Cart getCurrentCart(String cartId);
    void updateCart(String cartId, Cart cart);
    void addItem(String cartId, ProductDto productDto);
    void removeItem(String cartId, Long productId);
    void decreaseItemQuantity(String cartId, Long productId);
    void clearCart(String cartId);
    boolean isCartExists(String cartId);
}
