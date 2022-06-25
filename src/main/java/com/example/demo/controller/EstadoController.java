package com.example.demo.controller;

import com.example.demo.mapper.EstadoMapper;
import com.example.demo.model.Estado;
import com.example.demo.model.dto.EstadoDTO;
import com.example.demo.service.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoMapper estadoMapper;
    @Autowired
    private EstadoService estadoService;

    @Operation(summary = "Este metodo permite listar los estados registrados")
    @GetMapping
    public ResponseEntity<List<EstadoDTO>> listar(){
        try{
            List<Estado> estadoList = estadoService.listar();
            List<EstadoDTO> estadoDTOS = estadoMapper.toDTOList(estadoList);
            return new ResponseEntity<>(estadoDTOS, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite guardar los estados" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación")
    @PostMapping("/guardarEstado")
    public ResponseEntity<String> save(@RequestBody EstadoDTO estadoDTO){
        try {
            Estado estado = estadoMapper.toEntity(estadoDTO);
            return new ResponseEntity<>(estadoService.registrar(estado), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar los estados" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
    @PutMapping("/actualizarEstado")
    public ResponseEntity<String> modificar(@RequestBody EstadoDTO estadoDTO){
        try{
            return new ResponseEntity<>(estadoService.actualizar(estadoDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar los estados")
    @DeleteMapping("/eliminarEstado")
    public ResponseEntity<?> eliminarEstado(@RequestParam Long idEstado){
        try {
            estadoService.eliminar(idEstado);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }
}
