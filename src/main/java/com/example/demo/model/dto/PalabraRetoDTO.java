package com.example.demo.model.dto;

import com.example.demo.model.PalabrasReservadas;
import com.example.demo.model.Reto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class PalabraRetoDTO implements Serializable {

    private static final long serialVersionUID = 3255815825317201697L;

    private Long idPalabraReto;
    private Long idPalabrasReservadas;
    private Long idReto;
}
