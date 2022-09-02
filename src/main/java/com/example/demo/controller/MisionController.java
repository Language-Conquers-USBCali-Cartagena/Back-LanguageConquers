package com.example.demo.controller;

import com.example.demo.dao.MisionDAO;
import com.example.demo.mapper.MisionMapper;
import com.example.demo.model.Mision;
import com.example.demo.model.dto.MisionDTO;
import com.example.demo.service.MisionService;
import com.sendgrid.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/misiones")
public class MisionController {

    @Autowired
    MisionService misionService;

    @Autowired
    MisionMapper misionMapper;


    @Operation(summary = "Este metodo sirve para listar las misiones")
    @GetMapping
    public ResponseEntity<List<MisionDTO>> listarMisiones(){
        try{
            List<Mision> misions = misionService.ListarMisiones();
            return new ResponseEntity<>(misionMapper.toDTOList(misions), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite crear una mision")
    @PostMapping
    public ResponseEntity<String> crearMision(@RequestBody MisionDTO misionDTO){
        try{
            Mision mision = misionMapper.toEntity(misionDTO);
            String mensaje = misionService.crearMision(mision);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
