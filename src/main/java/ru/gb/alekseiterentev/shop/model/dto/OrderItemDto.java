package ru.gb.alekseiterentev.shop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.alekseiterentev.shop.model.Product;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private int price;
    private int quantity;
    private int totalPrice;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.price = product.getPrice();
        this.quantity = 1;
        this.totalPrice = product.getPrice();
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        totalPrice = quantity * price;
    }
}
