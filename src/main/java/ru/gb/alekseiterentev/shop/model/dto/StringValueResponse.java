package ru.gb.alekseiterentev.shop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StringValueResponse {

    private String value;

    public StringValueResponse(String value) {
        this.value = value;
    }
}
