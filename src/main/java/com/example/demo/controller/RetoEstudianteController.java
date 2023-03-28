package com.example.demo.controller;

import com.example.demo.service.RetoEstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retoEstudiante")
public class RetoEstudianteController {

    @Autowired
    RetoEstudianteService retoEstudianteService;


    @Operation(summary = "Este método trae el promedio de retos completados de los estudiantes")
    @GetMapping("/promedioRetosCompletados")
    public ResponseEntity<Integer> retosCompletados() throws Exception {
        int cantidad = retoEstudianteService.promedioRetosCompletadosEstudiantes();
        return new ResponseEntity<>(cantidad, HttpStatus.OK);
    }
}
