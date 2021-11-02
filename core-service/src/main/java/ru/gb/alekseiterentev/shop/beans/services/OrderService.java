package ru.gb.alekseiterentev.shop.beans.services;

import ru.gb.alekseiterentev.shop.model.Order;
import ru.gb.alekseiterentev.shop.model.dto.OrderDetailsDto;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order createOrder(OrderDetailsDto orderDetails, Principal principal);
    List<Order> findAllByUsername(String username);
    boolean checkThatUserOrderedProduct(Principal principal, Long productId);
    Optional<Order> findById(Long id);
    Order save(Order order);
}
