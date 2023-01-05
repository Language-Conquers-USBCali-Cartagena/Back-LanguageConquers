package com.example.demo.controller;

import com.example.demo.mapper.OrdenRetoIDEMapper;
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

    @Autowired
    OrdenRetoIDEMapper ordenRetoIDEMapper;

    @Operation(summary = "Este metodo permite verificar el intento")
    @PostMapping("/verificar")
    public ResponseEntity<List<OrdenRetoIDEDTO>> verificarIntento(@RequestBody List<OrdenRetoIDEDTO> ordenRetoIDEDTOS, @RequestParam Long idReto){
        try{

            return new ResponseEntity<>(ordenRetoIDEService.VerificarOrden(ordenRetoIDEDTOS, idReto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite listar todos los orden reto")
    @GetMapping
    public ResponseEntity<List<OrdenRetoIDEDTO>> listarOrdenRetoIDE(){
        try{
            List<OrdenRetoIDEDTO> ordenRetoIDEDTOS = ordenRetoIDEMapper.toDTOList( ordenRetoIDEService.findAll());
            return new ResponseEntity<>(ordenRetoIDEDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite crear un orden reto")
    @PostMapping
    public ResponseEntity<String> crearOrdenRetoIDE(@RequestBody OrdenRetoIDEDTO ordenRetoIDEDTO){
        try{
            String mensaje = ordenRetoIDEService.crarOrdenRet(ordenRetoIDEMapper.toEntity(ordenRetoIDEDTO));
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite lostar el ordenReto por idReto")
    @GetMapping("/porIdReto")
    public ResponseEntity<List<OrdenRetoIDEDTO>> listarPorIdReto(@RequestParam Long idReto){
        try{
            List<OrdenRetoIDEDTO> ordenRetoIDEDTOS = ordenRetoIDEMapper.toDTOList(ordenRetoIDEService.findByIdReto(idReto));
            return new ResponseEntity<>(ordenRetoIDEDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
