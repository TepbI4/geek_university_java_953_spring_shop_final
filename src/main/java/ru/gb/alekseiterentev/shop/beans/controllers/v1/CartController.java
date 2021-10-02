package ru.gb.alekseiterentev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.beans.services.CartService;
import ru.gb.alekseiterentev.shop.utils.Cart;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart getCartForCurrentUser() {
        return cartService.getCartForCurrentUser();
    }

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable Long productId) {
        cartService.addItem(productId);
    }

    @GetMapping("/decrease/{productId}")
    public void decrease(@PathVariable Long productId) {
        cartService.decreaseItemQuantity(productId);
    }

    @GetMapping("/remove/{productId}")
    public void remove(@PathVariable Long productId) {
        cartService.removeItem(productId);
    }
}
