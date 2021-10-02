package ru.gb.alekseiterentev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.beans.services.CartService;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.exceptions.ProductNotFoundException;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.utils.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    private void init() {
        cart = new Cart();
    }

    @Override
    public Cart getCartForCurrentUser() {
        return cart;
    }

    @Override
    public void addItem(Long productId) {
        if (cart.add(productId)) {
            return;
        }

        Product product = productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + productId + " not found"));
        cart.add(product);
    }

    @Override
    public void removeItem(Long productId) {
        cart.remove(productId);
    }

    @Override
    public void decreaseItemQuantity(Long productId) {
        cart.decrease(productId);
    }

    @Override
    public void clearCart() {
        cart.clear();
    }
}
