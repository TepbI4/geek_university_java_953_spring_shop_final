package ru.gb.alekseiterentev.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.alekseiterentev.shop.beans.services.CartService;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.model.Product;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CartTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private ProductService bookService;

    @BeforeEach
    public void initCart() {
        cartService.clearCart("test_cart");
    }

    @Test
    public void addToCartAndDecreaseTest() {
        Product book = new Product();
        book.setId(5L);
        book.setTitle("X");
        book.setPrice(100);

        Mockito.doReturn(Optional.of(book)).when(bookService).findById(5L);
        cartService.addItem("test_cart", 5L);
        cartService.addItem("test_cart", 5L);
        cartService.addItem("test_cart", 5L);
        assertEquals(1, cartService.getCartForCurrentUser("test_cart").getItems().size());

        cartService.decreaseItemQuantity("test_cart", 5L);
        cartService.decreaseItemQuantity("test_cart", 5L);
        cartService.decreaseItemQuantity("test_cart", 5L);
        cartService.decreaseItemQuantity("test_cart", 5L);
        assertEquals(0, cartService.getCartForCurrentUser("test_cart").getTotalCartPrice());
    }
}
