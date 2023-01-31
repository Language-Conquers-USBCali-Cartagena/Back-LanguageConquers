package com.example.demo.service.serviceImpl;

import com.example.demo.dao.LogroDAO;
import com.example.demo.model.Logro;
import com.example.demo.model.dto.LogroDTO;
import com.example.demo.service.LogroService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class LogroServiceImpl implements LogroService {

    @Autowired
    LogroDAO logroDAO;

    @Override
    public List<Logro> listarLogros() throws Exception {
        List<Logro> logros = logroDAO.findAll();
        if(logros.isEmpty()){
            throw new Exception("No hay logros disponibles.");
        }
        return logros;
    }

    @Override
    public Page<Logro> pageLogros(Pageable pageable) throws Exception {
        Page<Logro> logros= logroDAO.findAll(pageable);
        if(logros.isEmpty()){
            throw new Exception("No hay logros disponibles.");
        }
        return logros;
    }

    @Override
    public String registrar(Logro logro) throws Exception {
        validacionesCrear(logro);
        logroDAO.save(logro);
        return "Se guardo exitosamente el logro.";
    }

    @Override
    public String eliminar(Long idLogro) throws Exception {
        if(idLogro == null){
            throw new Exception("Se debe ingresar el id del logro.");
        }
        if(!logroDAO.existsById(idLogro)){
            throw new Exception("El id logro no existe.");
        }
        logroDAO.deleteById(idLogro);
        return "El logro se elimino exitosamente.";
    }

    @Override
    public String actualizar(LogroDTO logroDTO) throws Exception {
        Logro logro = null;
        validacionesActualizar(logroDTO);
        logro = logroDAO.findById(logroDTO.getIdLogro()).orElse(null);
        logro.setNombre(logroDTO.getNombre());
        logro.setImagen(logroDTO.getImagen());
        logro.setDescripcion(logroDTO.getDescripcion());
        logro.setFechaModificacion(logroDTO.getFechaModificacion());
        logro.setUsuarioModificador(logroDTO.getUsuarioModificador());
        logroDAO.save(logro);
        return "Se actualizo correctamente el logro.";
    }

    @Override
    public Logro findById(Long idLogro) throws Exception {
        if(idLogro == null){
            throw new Exception("Se debe ingresar el id del logro.");
        }
        if(!logroDAO.existsById(idLogro)){
            throw new Exception("El id logro no existe.");
        }
        return logroDAO.findById(idLogro).get();
    }


    private void validacionesCrear(Logro logro) throws Exception {
        if(logro.getNombre() == null || logro.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del logro.");
        }
        if(Validaciones.isStringLenght(logro.getNombre(),50)){
            throw new Exception("El nombre del logro es muy largo.");
        }

        if(logro.getDescripcion()== null || logro.getDescripcion().equals("")){
            throw new Exception("Debe ingresar una descripción del logro.");
        }
        if(Validaciones.isStringLenght(logro.getDescripcion(),300)){
            throw new Exception("La descripción es muy larga.");
        }
        if(logro.getImagen() == null || logro.getImagen().equals("")){
            throw new Exception("Debe ingresar una imagen.");
        }
        if(Validaciones.isStringLenght(logro.getImagen(),250)){
            throw new Exception("El nombre de la imagen o url es muy larga.");
        }
        if(logro.getUsuarioCreador()==null || logro.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(logro.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(logro.getFechaCreacion()==null || logro.getFechaCreacion().toString().equals("")) {
            throw new Exception("Debe ingresar una fecha de creación.");
        }
    }

    private void validacionesActualizar(LogroDTO logroDTO) throws Exception{

        if(logroDTO.getIdLogro() == null){
            throw new Exception("Debe ingresar el id del logro que desea actualizar.");
        }
        if(!logroDAO.existsById(logroDTO.getIdLogro())){
            throw new Exception("No se encontró un logro con ese id.");
        }
        if(logroDTO.getNombre() == null || logroDTO.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del logro.");
        }
        if(Validaciones.isStringLenght(logroDTO.getNombre(),50)){
            throw new Exception("El nombre del logro es muy largo.");
        }
        if(logroDTO.getDescripcion()== null || logroDTO.getDescripcion().equals("")){
            throw new Exception("Debe ingresar una descripción del logro.");
        }
        if(Validaciones.isStringLenght(logroDTO.getDescripcion(),300)){
            throw new Exception("La descripción es muy larga.");
        }
        if(logroDTO.getImagen() == null || logroDTO.getImagen().equals("")){
            throw new Exception("Debe ingresar una imagen.");
        }
        if(Validaciones.isStringLenght(logroDTO.getImagen(),250)){
            throw new Exception("El nombre de la imagen o url es muy larga.");
        }
        Date fechaActual = new Date();
        if(logroDTO.getUsuarioModificador()==null || logroDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(logroDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.");
        }
        if(logroDTO.getFechaModificacion()==null || logroDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        if(logroDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
