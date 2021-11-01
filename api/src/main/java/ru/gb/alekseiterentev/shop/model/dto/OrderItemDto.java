package ru.gb.alekseiterentev.shop.model.dto;

import java.math.BigDecimal;

public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalPrice;

    public OrderItemDto(ProductDto product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.price = product.getPrice();
        this.quantity = 1;
        this.totalPrice = product.getPrice();
    }

    public OrderItemDto() {
    }

    public OrderItemDto(Long productId, String productTitle, BigDecimal price, int quantity, BigDecimal totalPrice) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
