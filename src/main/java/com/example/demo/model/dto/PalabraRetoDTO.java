package com.example.demo.model.dto;

import com.example.demo.model.PalabrasReservadas;
import com.example.demo.model.Reto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PalabraRetoDTO implements Serializable {

    private static final long serialVersionUID = 3255815825317201697L;

    private Long idPalabraReto;
    private Long idPalabrasReservadas;
    private Long idReto;
}
