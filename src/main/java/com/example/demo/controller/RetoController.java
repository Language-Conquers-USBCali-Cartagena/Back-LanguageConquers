package com.example.demo.controller;

import com.example.demo.mapper.RetoMapper;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.service.RetoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retos")
public class RetoController {

    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;

    @Operation(summary = "Lista todos los retos")
    @GetMapping
    public ResponseEntity<List<RetoDTO>> listarRetos(){
        try {
            List<Reto> retos = retoService.listReto();
            return new ResponseEntity<>(retoMapper.toDTOList(retos), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Crea retos")
    @PostMapping
    public ResponseEntity<String> crearReto(@RequestBody  RetoDTO retoDTO){
        try {
            Reto reto = retoMapper.toEntity(retoDTO);
            return new ResponseEntity<>(retoService.crearreto(reto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
}
