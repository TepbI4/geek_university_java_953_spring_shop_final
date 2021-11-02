package ru.gb.alekseiterentev.shop.model.dto;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {
    private Long id;
    private String status;
    private List<OrderItemDto> orderItems;
    private String address;
    private String phone;
    private BigDecimal total;

    public OrderDto() {
    }

    public OrderDto(Long id, String status, List<OrderItemDto> orderItems, String address, String phone, BigDecimal total) {
        this.id = id;
        this.status = status;
        this.orderItems = orderItems;
        this.address = address;
        this.phone = phone;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ru.gb.alekseiterentev.shop.model.dto.OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ru.gb.alekseiterentev.shop.model.dto.OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
