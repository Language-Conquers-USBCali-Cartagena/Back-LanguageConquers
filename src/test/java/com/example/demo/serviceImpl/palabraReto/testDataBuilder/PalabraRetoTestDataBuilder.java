package com.example.demo.serviceImpl.palabraReto.testDataBuilder;

import com.example.demo.model.dto.PalabraRetoDTO;

public class PalabraRetoTestDataBuilder {

    private Long idPalabraReto;
    private Long idPalabrasReservadas;
    private Long idReto;

    public PalabraRetoTestDataBuilder() {
        idPalabraReto = 1L;
        idPalabrasReservadas = 1L;
        idReto = 1L;
    }

    public PalabraRetoTestDataBuilder conIdPalabraReto(Long idPalabraReto) {
        this.idPalabraReto = idPalabraReto;
        return this;
    }

    public PalabraRetoTestDataBuilder conIdPalabrasReservadas(Long idPalabrasReservadas) {
        this.idPalabrasReservadas = idPalabrasReservadas;
        return this;
    }

    public PalabraRetoTestDataBuilder conIdReto(Long idReto) {
        this.idReto = idReto;
        return this;
    }

    public PalabraRetoDTO build() {
        return new PalabraRetoDTO(idPalabraReto, idPalabrasReservadas, idReto);

    }
}