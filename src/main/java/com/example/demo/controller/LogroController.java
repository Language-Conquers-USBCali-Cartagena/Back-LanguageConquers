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

    @Operation(summary = "Este metodo permite listar los logros")
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

    @Operation(summary = "Este metodo permite listar los logros de manera paginada")
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
    @Operation(summary = "Este metodo permite crear un logro")
    @PostMapping
    public ResponseEntity<String> crearLogro(@RequestBody LogroDTO logroDTO){
        try{
            Logro logro = logroMapper.toEntity(logroDTO);
            String mensaje = logroService.save(logro);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite actualizar un logro")
    @PutMapping
    public ResponseEntity<String> actualizarLogro(@RequestBody LogroDTO logroDTO){
        try{
            Logro logro = logroMapper.toEntity(logroDTO);
            String mensaje = logroService.update(logro);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite eliminar un logro")
    public ResponseEntity<String> eliminarLogro(@RequestParam Long idLogro){
        try{
            String mensaje = logroService.delete(idLogro);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
