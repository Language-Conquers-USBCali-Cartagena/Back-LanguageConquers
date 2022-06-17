package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class SmsDTO implements Serializable {


    private static final long serialVersionUID = -3909977333130967128L;
    String number;
    String message;

}
