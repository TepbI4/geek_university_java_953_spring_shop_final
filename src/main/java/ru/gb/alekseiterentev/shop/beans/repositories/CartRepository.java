package ru.gb.alekseiterentev.shop.beans.repositories;

import org.springframework.stereotype.Component;
import ru.gb.alekseiterentev.shop.model.CartItem;
import ru.gb.alekseiterentev.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartRepository {

    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(Product product) {
        Optional<CartItem> cartItem = cartItems.stream().filter(item -> product.getId().equals(item.getProduct().getId()))
                .findFirst();
        if (cartItem.isPresent()) {
            cartItem.ifPresent(item -> {
                item.setQuantity(item.getQuantity() + 1);
                item.setTotal(item.getQuantity() * item.getProduct().getPrice());
            });
        } else {
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(1);
            item.setTotal(product.getPrice());
            cartItems.add(item);
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
