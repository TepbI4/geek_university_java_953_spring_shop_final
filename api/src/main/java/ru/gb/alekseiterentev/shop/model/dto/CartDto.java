package ru.gb.alekseiterentev.shop.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private List<OrderItemDto> items;
    private BigDecimal totalCartPrice;

    public CartDto() {
    }

    public CartDto(List<OrderItemDto> items, BigDecimal totalCartPrice) {
        this.items = items;
        this.totalCartPrice = totalCartPrice;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(BigDecimal totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }
}
