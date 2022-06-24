package com.example.demo.controller;

import com.example.demo.model.dto.OrdenRetoIDEDTO;
import com.example.demo.service.OrdenRetoIDEService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenRetoIDE")
public class OrdenRetoIDEController {

    @Autowired
    private OrdenRetoIDEService ordenRetoIDEService;

    @Operation(summary = "Este metodo permite verificar el intento")
    @PostMapping("/verificar")
    public ResponseEntity<List<OrdenRetoIDEDTO>> verificarIntento(@RequestBody List<OrdenRetoIDEDTO> ordenRetoIDEDTOS, @RequestParam Long idReto){
        try{

            return new ResponseEntity<>(ordenRetoIDEService.VerificarOrden(ordenRetoIDEDTOS, idReto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
