package com.example.demo.controller;

import com.example.demo.mapper.RolMapper;
import com.example.demo.model.Rol;
import com.example.demo.model.dto.RolDTO;
import com.example.demo.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @Autowired
    private RolMapper rolMapper;

    @Operation(summary = "Este metodo permite listar los roles")
    @GetMapping
    public ResponseEntity<List<RolDTO>> listar(){
        try{
            List<Rol> rolList = rolService.listar();
            List<RolDTO> rolDTOS = rolMapper.toDTOList(rolList);
            return new ResponseEntity<>(rolDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Este metodo permite crear un rol" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación")
    @PostMapping("/guardarRol")
    public ResponseEntity<String> save(@RequestBody RolDTO rolDTO){
        try {
            Rol rol = rolMapper.toEntity(rolDTO);
            return new ResponseEntity<>(rolService.registrar(rol), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar un rol" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
    @PutMapping("/actualizarRol")
    public ResponseEntity<String> modificar(@RequestBody RolDTO rolDTO){
        try{
            return new ResponseEntity<>(rolService.actualizar(rolDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar un rol")
    @DeleteMapping("/eliminarRol")
    public ResponseEntity<?> eliminarRol(@RequestParam Long idRol){
        try {
            rolService.eliminar(idRol);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

}
