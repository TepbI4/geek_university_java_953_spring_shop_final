package ru.gb.alekseiterentev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.beans.services.CartService;
import ru.gb.alekseiterentev.shop.beans.services.OrderService;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.beans.services.impl.UserService;
import ru.gb.alekseiterentev.shop.exceptions.ProductNotFoundException;
import ru.gb.alekseiterentev.shop.model.Order;
import ru.gb.alekseiterentev.shop.model.OrderItem;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.model.User;
import ru.gb.alekseiterentev.shop.model.dto.OrderDetailsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = userService.findByUsername(userDetails.getUsername());

        Order order = new Order();
        order.setPhone(orderDetailsDto.getPhone());
        order.setAddress(orderDetailsDto.getAddress());
        user.ifPresent(order::setUser);

        List<OrderItem> orderItems = new ArrayList<>();
        cartService.getCartForCurrentUser().getItems().forEach(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            Product product = productService.findById(orderItemDto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product with id: " + orderItemDto.getProductId() + " not found"));
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPrice(orderItemDto.getPrice());
            orderItems.add(orderItem);
        });
        order.setOrderItems(orderItems);

        orderService.createOrder(order);
        cartService.clearCart();
    }
}
