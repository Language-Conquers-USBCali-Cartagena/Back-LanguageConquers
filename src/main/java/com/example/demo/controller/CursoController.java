package com.example.demo.controller;

import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @Autowired
    CursoMapper cursoMapper;
    @Operation(summary = "Este metodo permite crear un curso")
    @PostMapping("/registrarCurso")
    public ResponseEntity<String> crearCurso(@RequestBody CursoDTO cursoDTO){
        try {
            String respuesta = cursoService.registrar(cursoMapper.toEntity(cursoDTO));
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite actualizar un curso" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
    @PutMapping("/actualizarCurso")
    public ResponseEntity<String> modificar(@RequestBody CursoDTO cursoDTO){
        try{
            return new ResponseEntity<>(cursoService.actualizar(cursoDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar un curso")
    @DeleteMapping("/eliminarCurso/{id}")
    public ResponseEntity<String> eliminarCurso(@PathVariable("id") Long idCurso){
        try {
            String mensaje = cursoService.eliminar(idCurso);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite buscar por id un curso")
    @GetMapping("/porId/{id}")
        public ResponseEntity<CursoDTO> cursoPorId (@PathVariable("id") Long idCurso){
        try{
            CursoDTO cursoDTO = cursoMapper.toDTO(cursoService.findById(idCurso));
            return new ResponseEntity<>(cursoDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite listar todos los cursos")
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listaCursos(){
        try{
            List<Curso> cursos = cursoService.findAll();
            List<CursoDTO> cursoDTOS = cursoMapper.toDTOList(cursos);
            return new ResponseEntity<>(cursoDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo perimite listar los cursos por correo estudiante")
    @GetMapping("/porCorreoEstudiante")
    public ResponseEntity<List<CursoDTO>> listarCursosPorCorreoEstudiante(@RequestParam String correoEstudiante){
        try{
            List<Curso> cursos = cursoService.findByCorreoEstudiante(correoEstudiante);
            List<CursoDTO> cursoDTOS = cursoMapper.toDTOList(cursos);
            return new ResponseEntity<>(cursoDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
