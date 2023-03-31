package com.example.demo.controller;

import com.example.demo.mapper.ArticulosAdquiridosMapper;
import com.example.demo.model.Articulos;
import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.service.ArticulosAdquiridosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/misArticulos")
public class ArticulosAdquiridosController {

    @Autowired
    ArticulosAdquiridosService articulosAdquiridosService;

    @Autowired
    ArticulosAdquiridosMapper articulosAdquiridosMapper;

    @Operation(summary = "Este método permite crear un artículo adquirido.")
    @PostMapping("/guardarArticuloAdquirido")
    public ResponseEntity<String> crearArticuloAdquirido(@RequestBody ArticulosAdquiridosDTO articulosAdquiridosDTO){
        try{
            ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
            return new ResponseEntity<>(articulosAdquiridosService.registrar(articulosAdquiridos), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar el artículo adquirido.")
    @PutMapping("/actualizarArticuloAdquirido")
    public ResponseEntity<String> actualizarArticuloAdquirido(@RequestBody ArticulosAdquiridosDTO articulosAdquiridosDTO){
        try{
            ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
            return new ResponseEntity<>(articulosAdquiridosService.actualizar(articulosAdquiridos), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método peromite eliminar un artículo adquirido.")
    @DeleteMapping("/eliminarArticuloAdquirido/{id}")
    public ResponseEntity<String> eliminarArticuloAdquirido(@PathVariable("id") Long idArticuloAdquirido){
        try{
            return new ResponseEntity<>(articulosAdquiridosService.eliminar(idArticuloAdquirido), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite listar todos los articulos adquiridos.")
    @GetMapping
    public ResponseEntity<List<ArticulosAdquiridosDTO>> listarArticulosAdquiridos(){
        try {
            List<ArticulosAdquiridos> articulosAdquiridosList = articulosAdquiridosService.findAll();
            List<ArticulosAdquiridosDTO> articulosAdquiridosDTOS = articulosAdquiridosMapper.toDTOList(articulosAdquiridosList);
            return new ResponseEntity<>(articulosAdquiridosDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id un artículo adquirido.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<ArticulosAdquiridosDTO> articuloAdquiridoPorId(@PathVariable("id")Long idArticuloAdquirido){
        try {
            ArticulosAdquiridosDTO articulosAdquiridosDTO = articulosAdquiridosMapper.toDTO(articulosAdquiridosService.findById(idArticuloAdquirido));
            return new ResponseEntity<>(articulosAdquiridosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
