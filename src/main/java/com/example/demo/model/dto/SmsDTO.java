package com.example.demo.model.dto;

import java.io.Serializable;

public class SmsDTO implements Serializable {


    private static final long serialVersionUID = -3909977333130967128L;
    String number;
    String message;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
