package com.example.demo.controller;

import com.example.demo.mapper.MisionMapper;
import com.example.demo.model.Mision;
import com.example.demo.model.dto.MisionDTO;
import com.example.demo.service.MisionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/misiones")
public class MisionController {

    @Autowired
    MisionService misionService;

    @Autowired
    MisionMapper misionMapper;


    @Operation(summary = "Este método sirve para listar las misiones.")
    @GetMapping
    public ResponseEntity<List<MisionDTO>> listarMisiones(){
        try{
            List<Mision> misions = misionService.ListarMisiones();
            return new ResponseEntity<>(misionMapper.toDTOList(misions), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite crear una misión.")
    @PostMapping
    public ResponseEntity<String> crearMision(@RequestBody MisionDTO misionDTO){
        try{
            Mision mision = misionMapper.toEntity(misionDTO);
            String mensaje = misionService.registrar(mision);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar una misión" +
            ", No se debe de ingresar el usuario creador y la fecha creación.")
    @PutMapping("/actualizarMision")
    public ResponseEntity<String> modificar(@RequestBody MisionDTO misionDTO){
        try{
            return new ResponseEntity<>(misionService.actualizar(misionDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            System.out.println(mensaje);
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar una misión.")
    @DeleteMapping("/eliminarMision/{id}")
    public ResponseEntity<String> eliminarMision(@PathVariable("id") Long idMision){
        try {
            String mensaje = misionService.eliminar(idMision);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id una misión.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<MisionDTO> misionPorId (@PathVariable("id") Long idMision){
        try{
            MisionDTO misionDTO = misionMapper.toDTO(misionService.findById(idMision));
            return new ResponseEntity<>(misionDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
