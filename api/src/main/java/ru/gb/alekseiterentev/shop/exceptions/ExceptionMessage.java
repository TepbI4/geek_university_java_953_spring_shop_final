package ru.gb.alekseiterentev.shop.exceptions;


import java.util.Date;

public class ExceptionMessage {

    private String message;
    private Date date;

    public ExceptionMessage(String message) {
        this.message = message;
        this.date = new Date();
    }

    public ExceptionMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
