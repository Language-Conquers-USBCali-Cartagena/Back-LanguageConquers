package com.example.demo.controller;

import com.example.demo.mapper.PalabrasReservadasMapper;
import com.example.demo.model.PalabrasReservadas;
import com.example.demo.model.dto.PalabrasReservadasDTO;
import com.example.demo.service.PalabrasReservadasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabrasReservadas")
public class PalabrasReservadasController {

    @Autowired
    PalabrasReservadasService palabrasReservadasService;

    @Autowired
    PalabrasReservadasMapper palabrasReservadasMapper;


    @Operation(summary = "Este método permite listar todas las palabras reservadas.")
    @GetMapping
    public ResponseEntity<List<PalabrasReservadasDTO>> listarPalabrasReservadas(){
        try{
            List<PalabrasReservadasDTO> palabrasReservadasDTOS = palabrasReservadasMapper.toDTOList(palabrasReservadasService.findAll());
            return new ResponseEntity<>(palabrasReservadasDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite crear una palabra reservada.")
    @PostMapping
    public ResponseEntity<String> crearPalabraReservada(@RequestBody PalabrasReservadasDTO palabrasReservadasDTO){
        try{
            String mensaje = palabrasReservadasService.crearPalabraResevada(palabrasReservadasMapper.toEntity(palabrasReservadasDTO));
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite procesar la respuesta de un reto")
    @PostMapping("/respuesta")
    public ResponseEntity<String> procesarRespuestaReto(@RequestBody List<PalabrasReservadasDTO> palabrasReservadasDTOs){
        try{
            //TODO: Asignar puntaje y monedas a estudiante

            return new ResponseEntity<>(palabrasReservadasService.procesarPalabraReservada(palabrasReservadasDTOs), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite obtener las palabras reservadas para un reto en especifico")
    @GetMapping("/palabras-por-reto")
    public ResponseEntity<List<PalabrasReservadasDTO>> palabrasReservadasReto(@RequestParam Long idReto){
        try {
            List<PalabrasReservadas> palabrasReservadas = palabrasReservadasService.findByIdReto(idReto);
            List<PalabrasReservadasDTO> palabrasReservadasDTOS = palabrasReservadasMapper.toDTOList(palabrasReservadas);
            return new ResponseEntity<>(palabrasReservadasDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
