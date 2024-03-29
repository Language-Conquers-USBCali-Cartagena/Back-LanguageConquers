package com.example.demo.controller;

import com.example.demo.mapper.ArticulosMapper;
import com.example.demo.model.Articulos;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.service.ArticulosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/articulo")
public class ArticulosController {
    @Autowired
    ArticulosService articulosService;

    @Autowired
    ArticulosMapper articulosMapper;

    @Operation(summary = "Este método permite crear un artículo.")
    @PostMapping("/guardarArticulo")
    public ResponseEntity<String> crearArticulo(@RequestBody ArticulosDTO articulosDTO){
        try{
            Articulos articulos = articulosMapper.toEntity(articulosDTO);
            System.out.println(articulos);
            return new ResponseEntity<>(articulosService.registrar(articulos), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite actualizar el artículo.")
    @PutMapping("/actualizarArticulo")
    public ResponseEntity<String> actualizarArticulo(@RequestBody ArticulosDTO articulosDTO){
        try{
            Articulos articulo = articulosMapper.toEntity(articulosDTO);

            return new ResponseEntity<>(articulosService.actualizar(articulo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite eliminar un artículo.")
    @DeleteMapping("/eliminarArticulo/{id}")
    public ResponseEntity<String> eliminarArticulo(@PathVariable("id") Long idArticulo){
        try{
            return new ResponseEntity<>(articulosService.eliminar(idArticulo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite listar todos los artículos.")
    @GetMapping
    public ResponseEntity<List<ArticulosDTO>> listarArticulos(){
        try{
            List<Articulos> articulosList = articulosService.findAll();
            List<ArticulosDTO> articulosDTOS = articulosMapper.toDTOList(articulosList);
            return new ResponseEntity<>(articulosDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id un artículo.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<ArticulosDTO> articuloPorId (@PathVariable("id") Long idArticulo){
        try{
            ArticulosDTO articulosDTO = articulosMapper.toDTO(articulosService.findById(idArticulo));
            return new ResponseEntity<>(articulosDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo trae la cantidad de artículos registrados")
    @GetMapping("/cantidadArticulos")
    public ResponseEntity<Integer> totalArticulos() throws Exception {
        int cantidad = articulosService.articulosRegistrados();
        return new ResponseEntity<>(cantidad, HttpStatus.OK);
    }

    @Operation(summary = "Este metodo permite traer los articulos obtenidos por el estudiante")
    @GetMapping("/articulosObtenidos")
    public ResponseEntity<List<ArticulosDTO>> articulosObtenidos(@RequestParam Long idEstudiante){
        try {
            List<Articulos> articulos = articulosService.articulosObtenidos(idEstudiante);
            List<ArticulosDTO> articulosDTOS = articulosMapper.toDTOList(articulos);
            return new ResponseEntity<>(articulosDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite traer los articulos no obtenidos por el estudiante")
    @GetMapping("/articulosNoObtenidos")
    public ResponseEntity<List<ArticulosDTO>> articulosNoObtenidos(@RequestParam Long idEstudiante){
        try {
            List<Articulos> articulos = articulosService.articulosNoObtenidos(idEstudiante);
            List<ArticulosDTO> articulosDTOS = articulosMapper.toDTOList(articulos);
            return new ResponseEntity<>(articulosDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
