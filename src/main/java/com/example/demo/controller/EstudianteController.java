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

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;


    @Autowired
    private EstudianteMapper estudianteMapper;

    @Operation(summary = "Este metodo permite listar los estudiante")
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

    @Operation(summary = "Este metodo permite crear un estudiante" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación")
    @PostMapping("/crearEstudiante")
    public ResponseEntity<String> crear(@RequestBody EstudianteDTO estudianteDTO){
        try {
           Estudiante estudiante =  estudianteMapper.toEntity(estudianteDTO);
            return new ResponseEntity<>(estudianteService.crearEstudiante(estudiante), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar el estudiante" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
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

    @Operation(summary = "Este metodo permite eliminar un estudiante")
    @DeleteMapping("/eliminarEstudiante")
    public ResponseEntity<?> eliminarEstudiante(@RequestParam Long idEstudiante){
        try {
            estudianteService.eliminar(idEstudiante);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }


}
