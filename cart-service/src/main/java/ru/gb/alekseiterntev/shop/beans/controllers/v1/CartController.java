package ru.gb.alekseiterntev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.model.dto.CartDto;
import ru.gb.alekseiterentev.shop.model.dto.StringValueResponse;
import ru.gb.alekseiterntev.shop.beans.services.CartService;
import ru.gb.alekseiterntev.shop.beans.utils.Cart;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @GetMapping("/generate")
    public StringValueResponse generateTempCartId() {
        String cartId = null;
        do {
            cartId = UUID.randomUUID().toString();
        } while (cartService.isCartExists(cartId));
        return new StringValueResponse(cartId);
    }

    @GetMapping("/{cartId}")
    public CartDto getCartForCurrentUser(@PathVariable String cartId) {
        return cartService.getCartForCurrentUser(cartId);
    }

    @GetMapping("/{cartId}/add/{productId}")
    public void addToCart(@PathVariable String cartId, @PathVariable Long productId) {
        cartService.addItem(cartId, productId);
    }

    @GetMapping("/{cartId}/decrease/{productId}")
    public void decrease(@PathVariable String cartId, @PathVariable Long productId) {
        cartService.decreaseItemQuantity(cartId, productId);
    }

    @GetMapping("/{cartId}/remove/{productId}")
    public void remove(@PathVariable String cartId, @PathVariable Long productId) {
        cartService.removeItem(cartId, productId);
    }
}
