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
            String mensaje = tipoMisionService.registrar(tipoMision);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar un tipoMision" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
    @PutMapping("/actualizarTipoMision")
    public ResponseEntity<String> modificar(@RequestBody TipoMisionDTO tipoMisionDTO){
        try{
            return new ResponseEntity<>(tipoMisionService.actualizar(tipoMisionDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar un tipoMision")
    @DeleteMapping("/eliminarTipoMision/{id}")
    public ResponseEntity<String> eliminarTipoMision(@PathVariable("id") Long idTipoMision){
        try {
            String mensaje = tipoMisionService.eliminar(idTipoMision);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite buscar por id un tipoMision")
    @GetMapping("/porId/{id}")
    public ResponseEntity<TipoMisionDTO> tipoMisionPorId (@PathVariable("id") Long idTipoMision){
        try{
            TipoMisionDTO tipoMisionDTO = tipoMisionMapper.toDTO(tipoMisionService.findById(idTipoMision));
            return new ResponseEntity<>(tipoMisionDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
