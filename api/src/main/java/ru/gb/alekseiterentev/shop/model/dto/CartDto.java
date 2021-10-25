package ru.gb.alekseiterentev.shop.model.dto;

import java.util.List;

public class CartDto {

    private List<OrderItemDto> items;
    private int totalCartPrice;

    public CartDto() {
    }

    public CartDto(List<OrderItemDto> items, int totalCartPrice) {
        this.items = items;
        this.totalCartPrice = totalCartPrice;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public int getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(int totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }
}
