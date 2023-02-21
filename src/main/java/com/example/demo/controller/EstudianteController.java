package com.example.demo.controller;

import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.Bitacora;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.AvatarDTO;
import com.example.demo.model.dto.BitacoraDTO;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;


    @Autowired
    private EstudianteMapper estudianteMapper;

    @Operation(summary = "Este método permite listar los estudiante.")
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listar(){
        try{

            List<Estudiante> estudianteList = estudianteService.listar();
            List<EstudianteDTO> estudianteDTOS = estudianteMapper.toDTOList(estudianteList);
            return new ResponseEntity<>(estudianteDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite crear un estudiante" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación.")
    @PostMapping("/crearEstudiante")
    public ResponseEntity<String> crear(@RequestBody EstudianteDTO estudianteDTO){
        try {
           Estudiante estudiante =  estudianteMapper.toEntity(estudianteDTO);
            return new ResponseEntity<>(estudianteService.crearEstudiante(estudiante), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar el estudiante" +
            ", No se debe de ingresar el usuario creador y la fecha creación.")
    @PutMapping("/actualizarEstudiante")
    public ResponseEntity<String> modificar(@RequestBody EstudianteDTO estudianteDTO){
        try{
            return new ResponseEntity<>(estudianteService.actualizar(estudianteDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            System.out.println(mensaje);
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar un estudiante.")
    @DeleteMapping("/eliminarEstudiante/{id}")
    public ResponseEntity<String> eliminarEstudiante(@PathVariable("id") Long idEstudiante){
        try {
            return new ResponseEntity<>(estudianteService.eliminar(idEstudiante), HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite saber si el correo del estudiante ya se encuentra registrado.")
    @GetMapping("/existePorCorreo")
    public ResponseEntity<Boolean> existePorCorreo(@RequestParam String correo){
        try {

            return new ResponseEntity<>(estudianteService.existePorCorreo(correo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite traer un estudiante por correo.")
    @GetMapping("/porCorrero")
    public ResponseEntity<EstudianteDTO> encontrarPorCorreo(@RequestParam String correo){
        try {

            EstudianteDTO estudianteDTO =  estudianteMapper.toDTO(estudianteService.findByCorreo(correo));
            return new ResponseEntity<>(estudianteDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite listar el ranking de los estudiantes.")
    @GetMapping("/rankingEstudiante")
    public ResponseEntity<List<EstudianteDTO>> rankingEstudiantes(){
        try{
            List<Estudiante> estudiantes = estudianteService.rankingEstudiante();
            List<EstudianteDTO> estudianteDTOList = estudianteMapper.toDTOList(estudiantes);
            return new ResponseEntity<>(estudianteDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id un estudiante.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<EstudianteDTO> estudiantePorId (@PathVariable("id") Long idEstudiante){
        try{
            EstudianteDTO estudianteDTO = estudianteMapper.toDTO(estudianteService.findById(idEstudiante));
            return new ResponseEntity<>(estudianteDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
