package ru.gb.alekseiterentev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.beans.services.CartService;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.exceptions.ProductNotFoundException;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.utils.Cart;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;

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

        Product product = productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + productId + " not found"));
        cart.add(product);
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
