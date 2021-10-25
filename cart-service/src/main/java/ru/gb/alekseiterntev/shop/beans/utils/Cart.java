package ru.gb.alekseiterntev.shop.beans.utils;


import lombok.Data;
import ru.gb.alekseiterentev.shop.model.dto.OrderItemDto;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {

    private List<OrderItemDto> items;
    private int totalCartPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto productDto) {
        for(OrderItemDto cartItem : items) {
            if (cartItem.getProductId().equals(productDto.getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getPrice());
                recalculateTotalPrice();
                return;
            }
        }
        items.add(new OrderItemDto(productDto.getId(), productDto.getTitle(), productDto.getPrice(), 1, productDto.getPrice()));
    }

    public void decrease(Long productId) {
        Iterator<OrderItemDto> orderItemsIter = items.iterator();
        while (orderItemsIter.hasNext()) {
            OrderItemDto item = orderItemsIter.next();
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() - 1);
                item.setTotalPrice(item.getQuantity() * item.getPrice());
                if (item.getQuantity() <= 0) {
                    orderItemsIter.remove();
                }
                recalculateTotalPrice();
                return;
            }
        }
    }

    public void remove(Long productId) {
        items.removeIf(cartItem -> cartItem.getProductId().equals(productId));
        recalculateTotalPrice();
    }

    public void clear() {
        items.clear();
        totalCartPrice = 0;
    }

    private void recalculateTotalPrice() {
        totalCartPrice = 0;
        items.forEach(cartItem -> totalCartPrice += cartItem.getTotalPrice());
    }
}
