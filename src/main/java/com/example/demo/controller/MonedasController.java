package com.example.demo.controller;

import com.example.demo.mapper.MonedasMapper;
import com.example.demo.model.Monedas;
import com.example.demo.model.dto.MonedasDTO;
import com.example.demo.service.MonedasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monedas")
public class MonedasController {

    @Autowired
    MonedasService monedasService;

    @Autowired
    MonedasMapper monedasMapper;


    @Operation(summary = "Este metodo permite listar todoas las monedas")
    @GetMapping
    public ResponseEntity<List<MonedasDTO>> listarMonedas(){
        try{
            List<MonedasDTO> monedasDTOs =  monedasMapper.toDTOList(monedasService.findAlList());
            return new ResponseEntity<>(monedasDTOs, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite crear una moneda")
    @PostMapping
    public ResponseEntity<String> crearMoneda(@RequestBody MonedasDTO monedasDTO){
        try{
            String mensaje = monedasService.crarMonedas(monedasMapper.toEntity(monedasDTO));
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
