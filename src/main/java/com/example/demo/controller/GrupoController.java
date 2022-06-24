package com.example.demo.controller;

import com.example.demo.mapper.GrupoMapper;
import com.example.demo.model.Grupo;
import com.example.demo.model.dto.GrupoDTO;
import com.example.demo.service.GrupoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoMapper grupoMapper;

    @Operation(summary = "Este metodo permite listar los grupos")
    @GetMapping
    public ResponseEntity<List<GrupoDTO>> listar(){
        List<Grupo> grupoList = grupoService.listar();
        List<GrupoDTO> grupoDTOS = grupoMapper.toDTOList(grupoList);
        return ResponseEntity.ok().body(grupoDTOS);
    }

    @Operation(summary = "Este metodo permite crear un grupo")
    @PostMapping("/guardarGrupo")
    public ResponseEntity<String> save(@RequestBody Grupo grupo){
        try {
            return new ResponseEntity(grupoService.registrar(grupo), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar un grupo")
    @PutMapping("/actualizarGrupo")
    public ResponseEntity<Grupo> modificar(@RequestBody GrupoDTO grupoDTO){
        try{
            return new ResponseEntity<>(grupoService.actualizar(grupoDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar un grupo")
    @DeleteMapping("/eliminarGrupo")
    public ResponseEntity<?> eliminarGrupo(@RequestParam Long idGrupo){
        try {
            grupoService.eliminar(idGrupo);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }
}
