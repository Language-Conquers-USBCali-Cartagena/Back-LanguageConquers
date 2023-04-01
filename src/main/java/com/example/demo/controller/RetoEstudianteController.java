package com.example.demo.controller;

import com.example.demo.mapper.RetoEstudianteMapper;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RetoEstudianteDTO;
import com.example.demo.service.RetoEstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retoEstudiante")
public class RetoEstudianteController {

    @Autowired
    RetoEstudianteService retoEstudianteService;

    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;


    @Operation(summary = "Este método permite listar todos los reto estudiantes.")
    @GetMapping
    public ResponseEntity<List<RetoEstudianteDTO>> listar(){
        try{
            List<RetoEstudiante> retoEstudianteList = retoEstudianteService.listar();
            List<RetoEstudianteDTO> retoEstudianteDTOS = retoEstudianteMapper.toDTOList(retoEstudianteList);
            return new ResponseEntity<>(retoEstudianteDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite crear un reto-estudiante, No se debe ingresar el usuario modificador y la fecha modificación")
    @PostMapping("/crearRetoEstudiante")
    public ResponseEntity<String> crear(@RequestBody RetoEstudianteDTO retoEstudianteDTO){
        try{
            RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
            System.out.println(retoEstudiante.getFechaEntrega());
            return new ResponseEntity<>(retoEstudianteService.crearRetoEstudiante(retoEstudiante), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar el reto-estudiante, no se debe ingresar el usuario creador y la fecha creación.")
    @PutMapping("/actualizarRetoEstudiante")
    public ResponseEntity<String> modificar(@RequestBody RetoEstudianteDTO retoEstudianteDTO){
        try{
            return new ResponseEntity<>(retoEstudianteService.actualizar(retoEstudianteDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar un reto-estudiante.")
    @DeleteMapping("/eliminarRetoEstudiante/{id}")
    public ResponseEntity<String> eliminarRetoEstudiante(@PathVariable("id") Long idRetoEstudiante){
        try{
            return new ResponseEntity<>(retoEstudianteService.eliminar(idRetoEstudiante), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar un reto-estudiante por su id.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<RetoEstudianteDTO> retoEstudiantePorId(@PathVariable("id") Long idRetoEstudiante){
        try{
            RetoEstudianteDTO retoEstudianteDTO = retoEstudianteMapper.toDTO(retoEstudianteService.findById(idRetoEstudiante));
            return new ResponseEntity<>(retoEstudianteDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar un reto-estudiante por el id del estudiante.")
    @GetMapping("/porIdEstudiante/{id}")
    public ResponseEntity<List<RetoEstudianteDTO>> retoEstudiantePorIdEstudiante(@PathVariable("id") Long idEstudiante){
        try{
            List<RetoEstudiante> retoEstudianteList = retoEstudianteService.listarPorIdEstudiante(idEstudiante);
            List<RetoEstudianteDTO> retoEstudianteDTOList = retoEstudianteMapper.toDTOList(retoEstudianteList);
            return new ResponseEntity<>(retoEstudianteDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar un reto-estudiante por el id del reto")
    @GetMapping("/porIdReto/{id}")
    public ResponseEntity<List<RetoEstudianteDTO>> retoEstudiantePorIdReto(@PathVariable("id") Long idReto){
        try{
            List<RetoEstudiante> retoEstudianteList = retoEstudianteService.listarPorIdReto(idReto);
            List<RetoEstudianteDTO> retoEstudianteDTOList = retoEstudianteMapper.toDTOList(retoEstudianteList);
            return new ResponseEntity<>(retoEstudianteDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método trae el promedio de retos completados de los estudiantes")
    @GetMapping("/promedioRetosCompletados")
    public ResponseEntity<Integer> retosCompletados() throws Exception {
        int cantidad = retoEstudianteService.promedioRetosCompletadosEstudiantes();
        return new ResponseEntity<>(cantidad, HttpStatus.OK);
    }
}
