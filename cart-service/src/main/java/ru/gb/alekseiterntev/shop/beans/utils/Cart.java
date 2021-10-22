package ru.gb.alekseiterntev.shop.beans.utils;


import lombok.Data;
import ru.gb.alekseiterentev.shop.model.dto.OrderItemDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<OrderItemDto> items;
    private int totalCartPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

//    public boolean add(Long productId) {
//        for(OrderItemDto cartItem : items) {
//            if (cartItem.getProductId().equals(productId)) {
//                cartItem.changeQuantity(1);
//                recalculateTotalPrice();
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void add(Product product) {
//        items.add(new OrderItemDto(product));
//        recalculateTotalPrice();
//    }
//
//    public void decrease(Long productId) {
//        for (OrderItemDto item : items) {
//            if (item.getProductId().equals(productId)) {
//                item.changeQuantity(-1);
//                if (item.getQuantity() <= 0) {
//                    remove(productId);
//                }
//                recalculateTotalPrice();
//                return;
//            }
//        }
//    }

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
