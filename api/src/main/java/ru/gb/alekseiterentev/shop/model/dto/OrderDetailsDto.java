package ru.gb.alekseiterentev.shop.model.dto;


public class OrderDetailsDto {
    private String phone;
    private String address;

    public OrderDetailsDto() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
