package ru.gb.alekseiterentev.shop.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ExceptionMessage {

    private String message;
    private Date date;

    public ExceptionMessage(String message) {
        this.message = message;
        this.date = new Date();
    }
}
