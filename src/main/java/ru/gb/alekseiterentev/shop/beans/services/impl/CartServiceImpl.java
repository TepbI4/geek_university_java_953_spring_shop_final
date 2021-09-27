package ru.gb.alekseiterentev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.beans.repositories.CartRepository;
import ru.gb.alekseiterentev.shop.beans.services.CartService;
import ru.gb.alekseiterentev.shop.model.CartItem;
import ru.gb.alekseiterentev.shop.model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public void addToCart(Product product) {
        cartRepository.addToCart(product);
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartRepository.getCartItems();
    }
}
