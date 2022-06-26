package com.example.demo.controller;


import com.example.demo.mapper.BitacoraMapper;
import com.example.demo.model.Bitacora;
import com.example.demo.model.dto.BitacoraDTO;
import com.example.demo.service.BitacoraService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bitacora")
public class BitacoraController {

    @Autowired
    private BitacoraService bitacoraService;

    @Autowired
    private BitacoraMapper bitacoraMapper;

    @Operation(summary = "Este metodo permite listar el registro de acceso de los usuarios a la plataforma")
    @GetMapping
    public ResponseEntity<List<BitacoraDTO>> listar(){
        try{

            List<Bitacora> bitacoraList = bitacoraService.listar();
            List<BitacoraDTO> bitacoraDTOS = bitacoraMapper.toDTOList(bitacoraList);
            return new ResponseEntity<>(bitacoraDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite guardar el registro de acceso del usuario a la plataforma" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación")
    @PostMapping("/guardarBitacora")
    public ResponseEntity<String> save(@RequestBody BitacoraDTO bitacoraDTO){
        try {
            Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
            return new ResponseEntity<>(bitacoraService.registrar(bitacora), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar el registro de acceso del usuario a la plataforma" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
    @PutMapping("/actualizarBitacora")
    public ResponseEntity<String> modificar(@RequestBody BitacoraDTO bitacoraDTO){
        try{
            return new ResponseEntity<>(bitacoraService.actualizar(bitacoraDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar el registro de acceso del usuario a la plataforma")
    @DeleteMapping("/eliminarBitacora")
    public ResponseEntity<?> eliminarBitacora(@RequestParam Long idBitacora){
        try {
            bitacoraService.eliminar(idBitacora);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }



}
