package ru.gb.alekseiterentev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.beans.repositories.OrderRepository;
import ru.gb.alekseiterentev.shop.beans.services.OrderService;
import ru.gb.alekseiterentev.shop.model.Order;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }
}
