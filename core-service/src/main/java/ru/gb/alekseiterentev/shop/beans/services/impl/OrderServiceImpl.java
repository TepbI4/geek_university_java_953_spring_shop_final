package ru.gb.alekseiterentev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.beans.repositories.OrderRepository;
import ru.gb.alekseiterentev.shop.beans.services.OrderService;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.exceptions.ProductNotFoundException;
import ru.gb.alekseiterentev.shop.model.Order;
import ru.gb.alekseiterentev.shop.model.OrderItem;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.model.User;
import ru.gb.alekseiterentev.shop.model.dto.OrderDetailsDto;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
//    private final CartService cartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Override
    public void createOrder(OrderDetailsDto orderDetails, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());

        Order order = new Order();
        order.setPhone(orderDetails.getPhone());
        order.setAddress(orderDetails.getAddress());
        user.ifPresent(order::setUser);

//        List<OrderItem> orderItems = new ArrayList<>();
//        cartService.getCartForCurrentUser(principal.getName()).getItems().forEach(orderItemDto -> {
//            OrderItem orderItem = new OrderItem();
//            Product product = productService.findById(orderItemDto.getProductId())
//                    .orElseThrow(() -> new ProductNotFoundException("Product with id: " + orderItemDto.getProductId() + " not found"));
//            orderItem.setOrder(order);
//            orderItem.setProduct(product);
//            orderItem.setPrice(orderItemDto.getPrice());
//            orderItem.setQuantity(orderItemDto.getQuantity());
//            orderItems.add(orderItem);
//        });
//        order.setOrderItems(orderItems);
//        order.setTotal(orderItems.stream().map(OrderItem::getPrice).reduce(0, Integer::sum));
//
//        orderRepository.save(order);
//        cartService.clearCart(principal.getName());
    }

    @Override
    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    @Override
    public boolean checkThatUserOrderedProduct(Principal principal, Long productId) {
        if (Objects.isNull(principal)) {
            return false;
        }
        return orderRepository.findUserByProduct(productId, principal.getName()).isPresent();
    }
}
