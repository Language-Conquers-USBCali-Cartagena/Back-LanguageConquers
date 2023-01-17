package com.example.demo.controller;

import com.example.demo.mapper.MonedasMapper;
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


    @Operation(summary = "Este método permite listar todas las monedas.")
    @GetMapping
    public ResponseEntity<List<MonedasDTO>> listarMonedas(){
        try{
            List<MonedasDTO> monedasDTOs =  monedasMapper.toDTOList(monedasService.findAlList());
            return new ResponseEntity<>(monedasDTOs, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite crear una moneda.")
    @PostMapping
    public ResponseEntity<String> crearMoneda(@RequestBody MonedasDTO monedasDTO){
        try{
            String mensaje = monedasService.registrar(monedasMapper.toEntity(monedasDTO));
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar una moneda" +
            ", No se debe de ingresar el usuario creador y la fecha creación.")
    @PutMapping("/actualizarMoneda")
    public ResponseEntity<String> modificar(@RequestBody MonedasDTO monedasDTO){
        try{
            return new ResponseEntity<>(monedasService.actualizar(monedasDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar una moneda.")
    @DeleteMapping("/eliminarMoneda/{id}")
    public ResponseEntity<String> eliminarMoneda(@PathVariable("id") Long idMoneda){
        try {
            String mensaje = monedasService.eliminar(idMoneda);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id una moneda.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<MonedasDTO> monedaPorId (@PathVariable("id") Long idMoneda){
        try{
           MonedasDTO monedasDTO = monedasMapper.toDTO(monedasService.findById(idMoneda));
            return new ResponseEntity<>(monedasDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
