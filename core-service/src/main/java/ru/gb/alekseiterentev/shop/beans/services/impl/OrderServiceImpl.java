package ru.gb.alekseiterentev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.alekseiterentev.shop.beans.integration.CartServiceIntegration;
import ru.gb.alekseiterentev.shop.beans.repositories.OrderRepository;
import ru.gb.alekseiterentev.shop.beans.services.OrderService;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.exceptions.ResourceNotFoundException;
import ru.gb.alekseiterentev.shop.model.Order;
import ru.gb.alekseiterentev.shop.model.OrderItem;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.model.User;
import ru.gb.alekseiterentev.shop.model.dto.OrderDetailsDto;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createOrder(OrderDetailsDto orderDetails, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());

        Order order = new Order();
        order.setPhone(orderDetails.getPhone());
        order.setAddress(orderDetails.getAddress());
        user.ifPresent(order::setUser);

        List<OrderItem> orderItems = new ArrayList<>();
        cartServiceIntegration.getUserCartDto(principal).getItems().forEach(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            Product product = productService.findById(orderItemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + orderItemDto.getProductId() + " not found"));
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPrice(orderItemDto.getPrice());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItems.add(orderItem);
        });
        order.setOrderItems(orderItems);
        order.setTotal(orderItems.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        orderRepository.save(order);
        cartServiceIntegration.clear(principal);
        return order;
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

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}
