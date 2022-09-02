package com.example.demo.controller;

import com.example.demo.mapper.NivelMisionMapper;
import com.example.demo.model.NivelMision;
import com.example.demo.model.dto.NivelMisionDTO;
import com.example.demo.service.NivelMisionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nivelMision")
public class NivelMisionController {

    @Autowired
    NivelMisionService nivelMisionService;

    @Autowired
    NivelMisionMapper nivelMisionMapper;

    @Operation(summary = "Este metodo permite listar todos los NivelMision")
    @GetMapping
    public ResponseEntity<List<NivelMisionDTO>> listarNivelMision(){
        try{
            List<NivelMision> nivelMisions = nivelMisionService.findAll();
            List<NivelMisionDTO> nivelMisionDTOS = nivelMisionMapper.toDTOList(nivelMisions);
            return new ResponseEntity<>(nivelMisionDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite crear un NivelMision")
    @PostMapping
    public ResponseEntity<String> crearNivelMision(@RequestBody NivelMisionDTO nivelMisionDTO){
        try{
            NivelMision nivelMision = nivelMisionMapper.toEntity(nivelMisionDTO);
            String mensaje = nivelMisionService.crearNivelMision(nivelMision);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
