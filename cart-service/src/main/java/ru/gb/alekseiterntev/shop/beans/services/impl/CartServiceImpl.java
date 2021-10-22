package ru.gb.alekseiterntev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.exceptions.ProductNotFoundException;
import ru.gb.alekseiterntev.shop.beans.services.CartService;
import ru.gb.alekseiterntev.shop.beans.utils.Cart;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    @Override
    public Cart getCartForCurrentUser(String cartId) {
        if (!isCartExists(cartId)) {
            redisTemplate.opsForValue().set(cartId, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartId);
    }

    @Override
    public void updateCart(String cartId, Cart cart) {
        redisTemplate.opsForValue().set(cartId, cart);
    }

    @Override
    public void addItem(String cartId, Long productId) {
        Cart cart = getCartForCurrentUser(cartId);
        if (cart.add(productId)) {
            updateCart(cartId, cart);
            return;
        }

//        Product product = productService.findById(productId)
//                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + productId + " not found"));
//        cart.add(product);
        updateCart(cartId, cart);
    }

    @Override
    public void removeItem(String cartId, Long productId) {
        Cart cart = getCartForCurrentUser(cartId);
        cart.remove(productId);
        updateCart(cartId, cart);
    }

    @Override
    public void decreaseItemQuantity(String cartId, Long productId) {
        Cart cart = getCartForCurrentUser(cartId);
        cart.decrease(productId);
        updateCart(cartId, cart);
    }

    @Override
    public void clearCart(String cartId) {
        Cart cart = getCartForCurrentUser(cartId);
        cart.clear();
        updateCart(cartId, cart);
    }

    @Override
    public boolean isCartExists(String cartId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(cartId));
    }
}
