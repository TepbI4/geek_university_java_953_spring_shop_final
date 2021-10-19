package ru.gb.alekseiterentev.shop.beans.services;

import ru.gb.alekseiterentev.shop.model.Order;
import ru.gb.alekseiterentev.shop.model.dto.OrderDetailsDto;

import java.security.Principal;
import java.util.List;

public interface OrderService {

    void createOrder(OrderDetailsDto orderDetails, Principal principal);
    List<Order> findAllByUsername(String username);
    boolean checkThatUserOrderedProduct(Principal principal, Long productId);
}
