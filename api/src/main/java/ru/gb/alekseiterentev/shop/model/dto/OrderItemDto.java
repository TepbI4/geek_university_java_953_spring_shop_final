package ru.gb.alekseiterentev.shop.model.dto;

public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private int price;
    private int quantity;
    private int totalPrice;

    public OrderItemDto(ProductDto product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.price = product.getPrice();
        this.quantity = 1;
        this.totalPrice = product.getPrice();
    }

    public OrderItemDto() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
