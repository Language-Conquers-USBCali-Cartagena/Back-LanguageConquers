package com.example.demo.controller;

import com.example.demo.mapper.ArticulosMapper;
import com.example.demo.model.Articulos;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.service.ArticulosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
public class ArticulosController {
    @Autowired
    ArticulosService articulosService;

    @Autowired
    ArticulosMapper articulosMapper;

    @Operation(summary = "Este metodo permite crear un articulo")
    @PostMapping
    public ResponseEntity<String> crearArticulo(@RequestBody ArticulosDTO articulosDTO){
        try{
            Articulos articulos = articulosMapper.toEntity(articulosDTO);
            String mensaje = articulosService.save(articulos);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite actualizar el articulo")
    @PutMapping
    public ResponseEntity<String> actualizarArticulo(@RequestBody ArticulosDTO articulosDTO){
        try{
            Articulos articulos = articulosMapper.toEntity(articulosDTO);
            String mensaje = articulosService.update(articulos);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite eliminar un articuli")
    @DeleteMapping
    public ResponseEntity<String> eliminarArticulo(@RequestParam Long idarticulio){
        try{
            String mensaje = articulosService.delete(idarticulio);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite listar todos los articulos")
    @GetMapping
    public ResponseEntity<List<ArticulosDTO>> listarArticulos(){
        try{
            List<ArticulosDTO> articulosDTOS = articulosMapper.toDTOList(articulosService.findAll());
            return new ResponseEntity<>(articulosDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
