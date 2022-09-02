package com.example.demo.controller;

import com.example.demo.mapper.TipoMisionMapper;
import com.example.demo.model.TipoMision;
import com.example.demo.model.dto.TipoMisionDTO;
import com.example.demo.service.TipoMisionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoMision")
public class TipoMisionController {

    @Autowired
    TipoMisionService tipoMisionService;

    @Autowired
    TipoMisionMapper tipoMisionMapper;

    @Operation(summary = "Este metodo permite listar todos los tipoMision")
    @GetMapping
    public ResponseEntity<List<TipoMisionDTO>> listarTipoMision(){
        try{
            List<TipoMision> tipoMisiones = tipoMisionService.findAll();
            List<TipoMisionDTO> tipoMisionDTOS = tipoMisionMapper.toDTOList(tipoMisiones);

            return new ResponseEntity<>(tipoMisionDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite crear ub TipoMision")
    @PostMapping
    public ResponseEntity<String> crearTipoMision(@RequestBody TipoMisionDTO tipoMisionDTO){
        try{
            TipoMision tipoMision = tipoMisionMapper.toEntity(tipoMisionDTO);
            String mensaje = tipoMisionService.crearTipoMision(tipoMision);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
