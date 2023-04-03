package com.example.demo.controller;

import com.example.demo.mapper.LogroMapper;
import com.example.demo.model.Logro;
import com.example.demo.model.dto.LogroDTO;
import com.example.demo.service.LogroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logro")
public class LogroController {

    @Autowired
    private LogroService logroService;
    @Autowired
    private LogroMapper logroMapper;

    @Operation(summary = "Este método permite listar los logros.")
    @GetMapping
    public ResponseEntity<List<LogroDTO>> listarLogros() throws Exception{
        try{
            List<Logro> logros = logroService.listarLogros();
            List<LogroDTO> logroDTOs = logroMapper.toDTOList(logros);
            return new ResponseEntity<>(logroDTOs, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite listar los logros de manera paginada.")
    @GetMapping("/logrosPaginado")
    public ResponseEntity<List<LogroDTO>> listarLogrosPaginado(@RequestParam Integer page, @RequestParam Integer size) throws Exception{
        try{
            Pageable pageable = PageRequest.of(page, size);
            Page<Logro> logros = logroService.pageLogros(pageable);
            List<LogroDTO> logroDTOs = logroMapper.toDTOList(logros);
            return new ResponseEntity<>(logroDTOs, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite crear un logro.")
    @PostMapping("/guardarLogro")
    public ResponseEntity<String> crearLogro(@RequestBody LogroDTO logroDTO){
        try{
            Logro logro = logroMapper.toEntity(logroDTO);
            String mensaje = logroService.registrar(logro);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite actualizar un logro")
    @PutMapping("/actualizarLogro")
    public ResponseEntity<String> actualizarLogro(@RequestBody LogroDTO logroDTO){
        try{
            String mensaje = logroService.actualizar(logroDTO);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite eliminar un logro.")
    @DeleteMapping("/eliminarLogro/{id}")
    public ResponseEntity<String> eliminarLogro(@PathVariable("id") Long idLogro){
        try{
            String mensaje = logroService.eliminar(idLogro);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id un logro.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<LogroDTO> logroPorId (@PathVariable("id") Long idLogro){
        try{
            LogroDTO logroDTO = logroMapper.toDTO(logroService.findById(idLogro));
            return new ResponseEntity<>(logroDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método trae la cantidad de logros registrados")
    @GetMapping("/cantidadLogros")
    public ResponseEntity<Integer> totalLogros() throws Exception {
        int cantidad = logroService.logrosRegistrados();
        return new ResponseEntity<>(cantidad, HttpStatus.OK);
    }

    @Operation(summary = "Este metodo retorna los logros obtenidos por estudiante")
    @GetMapping("/logrosObtenidos")
    public ResponseEntity<List<LogroDTO>> logrosObtenidos(@RequestParam Long idEstudiante){
        try {
            List<Logro> logros = logroService.logrosObtenidos(idEstudiante);
            List<LogroDTO> logroDTOS = logroMapper.toDTOList(logros);
            return new ResponseEntity<>(logroDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo retorna los logros no obtenidos de un estudiante")
    @GetMapping("/logrosNoObtenidos")
    public ResponseEntity<List<LogroDTO>> logrosNoObtenidosEstudiante(@RequestParam Long idEstudiante){
        try {
            List<Logro> logroes = logroService.logrosNoObtenidos(idEstudiante);
            List<LogroDTO> logroDTOS = logroMapper.toDTOList(logroes);
            return new ResponseEntity<>(logroDTOS, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite obtener el progreso de los logros por estudiante")
    @GetMapping("/progresoLogro")
    public ResponseEntity<Integer> progresoLogros(Long idEstudiante){
        try {
            Integer respuesta = logroService.progresoLogro(idEstudiante);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
