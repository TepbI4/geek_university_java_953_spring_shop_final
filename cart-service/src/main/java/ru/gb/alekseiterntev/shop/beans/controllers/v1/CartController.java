package ru.gb.alekseiterntev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.model.dto.CartDto;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;
import ru.gb.alekseiterentev.shop.model.dto.StringValueResponse;
import ru.gb.alekseiterntev.shop.beans.integration.ProductServiceIntegration;
import ru.gb.alekseiterntev.shop.beans.services.CartService;
import ru.gb.alekseiterntev.shop.beans.utils.Cart;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductServiceIntegration productServiceIntegration;

    @GetMapping("/generate")
    public StringValueResponse generateTempCartId() {
        String cartId = null;
        do {
            cartId = UUID.randomUUID().toString();
        } while (cartService.isCartExists(cartId));
        return new StringValueResponse(cartId);
    }

    @GetMapping("/{uuid}")
    public CartDto getCartForCurrentUser(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        Cart cart = cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
        return new CartDto(cart.getItems(), cart.getTotalCartPrice());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void addToCart(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        cartService.addItem(getCurrentCartUuid(username, uuid), product);
    }

    @GetMapping("/{uuid}/decrease/{productId}")
    public void decrease(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decreaseItemQuantity(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(@RequestHeader String username, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
