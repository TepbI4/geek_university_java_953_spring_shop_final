package ru.gb.alekseiterentev.shop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.alekseiterentev.shop.model.Product;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private int price;

    public ProductDto(Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
    }
}
