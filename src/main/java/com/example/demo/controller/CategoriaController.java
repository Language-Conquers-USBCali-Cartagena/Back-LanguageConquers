package com.example.demo.controller;

import com.example.demo.mapper.CategoriaMapper;
import com.example.demo.model.Categoria;
import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Operation(summary = "Este método permite listar las categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar(){
        try{
            List<Categoria> categoriaList = categoriaService.findAll();
            List<CategoriaDTO> categoriaDTOS = categoriaMapper.toDTOList(categoriaList);
            return new ResponseEntity<>(categoriaDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id una categoría.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<CategoriaDTO> categoriaPorId(@PathVariable("id")Long idCtegoria){
        try{
            CategoriaDTO categoriaDTO = categoriaMapper.toDTO(categoriaService.findById(idCtegoria));
            return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite guardar las categorias, no se debe ingresar el usuario modificador y la fecha de modificación.")
    @PostMapping("/guardarCategoria")
    public ResponseEntity<String> save (@RequestBody CategoriaDTO categoriaDTO){
        try {
            Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
            return new ResponseEntity<>(categoriaService.registrar(categoria), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar una categoria, no se debe ingresar el usuario creador y la fecha de creación. ")
    @PutMapping("/actualizarCategoria")
    public ResponseEntity<String> actualizar(@RequestBody CategoriaDTO categoriaDTO){
        try{
            return new ResponseEntity<>(categoriaService.actualizar(categoriaDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar una categoría.")
    @DeleteMapping("/eliminarCategoria/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable("id") Long idCategoria){
        try {
            return new ResponseEntity<>(categoriaService.eliminar(idCategoria), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
