package ru.gb.alekseiterntev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;
import ru.gb.alekseiterntev.shop.beans.services.CartService;
import ru.gb.alekseiterntev.shop.beans.utils.Cart;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Cart getCurrentCart(String cartId) {
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
    public void addItem(String cartId, ProductDto productDto) {
        execute(cartId, c -> {
            c.add(productDto);
        });
    }

    @Override
    public void removeItem(String cartId, Long productId) {
        execute(cartId, c -> c.remove(productId));
    }

    @Override
    public void decreaseItemQuantity(String cartId, Long productId) {
        execute(cartId, c -> c.decrease(productId));
    }

    @Override
    public void clearCart(String cartId) {
        execute(cartId, Cart::clear);
    }

    @Override
    public boolean isCartExists(String cartId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(cartId));
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}
