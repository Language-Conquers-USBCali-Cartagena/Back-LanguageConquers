package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredencialesDTO {

    private Long idCredencial;
    private String cuenta;
    private String password;
    private String url;
    private String plataforma;
}
