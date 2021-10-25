package ru.gb.alekseiterentev.shop.model.dto;


public class StringValueResponse {

    private String value;

    public StringValueResponse(String value) {
        this.value = value;
    }

    public StringValueResponse() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
