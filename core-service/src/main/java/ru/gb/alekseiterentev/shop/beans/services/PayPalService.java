package ru.gb.alekseiterentev.shop.beans.services;

import com.paypal.orders.OrderRequest;

public interface PayPalService {

    OrderRequest createOrderRequest(Long orderId);
}
