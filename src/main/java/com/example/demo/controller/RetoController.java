package com.example.demo.controller;

import com.example.demo.mapper.RetoMapper;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.AvatarDTO;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.service.RetoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reto")
public class RetoController {

    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;

    @Operation(summary = "Este método permite listar todos los retos.")
    @GetMapping
    public ResponseEntity<List<RetoDTO>> listarRetos(){
        try {
            List<Reto> retos = retoService.listReto();
            return new ResponseEntity<>(retoMapper.toDTOList(retos), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite crear un reto.")
    @PostMapping("/guardarReto")
    public ResponseEntity<String> crearReto(@RequestBody  RetoDTO retoDTO){
        try {

            Reto reto = retoMapper.toEntity(retoDTO);
            return new ResponseEntity<>(retoService.registrar(reto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id un reto.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<RetoDTO> retoPorId (@PathVariable("id") Long idReto){
        try{
            RetoDTO retoDTO= retoMapper.toDTO(retoService.findById(idReto));
            return new ResponseEntity<>(retoDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar los retos" +
            ", No se debe de ingresar el usuario creador y la fecha creación.")
    @PutMapping("/actualizarReto")
    public ResponseEntity<String> modificar(@RequestBody RetoDTO retoDTO){
        try{
            Reto reto = retoMapper.toEntity(retoDTO);
            return new ResponseEntity<>(retoService.actualizar(reto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar un reto.")
    @DeleteMapping("/eliminarReto/{id}")
    public ResponseEntity<String> eliminarReto(@PathVariable("id") Long idReto){
        try {
            String mensaje = retoService.eliminar(idReto);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método trae el promedio de monedas asignadas a los retos")
    @GetMapping("/promedioMonedasRetos")
    public ResponseEntity<Integer> promedioMonedasRetos() throws Exception {
        int cantidad = retoService.promedioMonedasRetos();
        return new ResponseEntity<>(cantidad, HttpStatus.OK);
    }

    @Operation(summary = "Este método trae la cantidad de retos registrados")
    @GetMapping("/cantidadRetos")
    public ResponseEntity<Integer> totalRetos() throws Exception {
        int cantidad = retoService.retosRegistrados();
        return new ResponseEntity<>(cantidad, HttpStatus.OK);
    }


}
