package com.example.demo.service.serviceImpl;

import com.example.demo.dao.EstudianteDAO;
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

    @Autowired
    EstudianteDAO estudianteDAO;

    @Override
    public List<Logro> logrosObtenidos(Long idEstudiante) throws Exception {
        return logroDAO.logrosObtenidos(idEstudiante);
    }

    @Override
    public List<Logro> logrosNoObtenidos(Long idEstudiante) throws Exception {
        return logroDAO.logrosNoObtenidos(idEstudiante);
    }

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
    public String actualizar(Logro logro) throws Exception {
        validacionesActualizar(logro);
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

    @Override
    public int logrosRegistrados() throws Exception {
        int cantidadLogros = logroDAO.logrosRegistrados();
        return cantidadLogros;
    }

    @Override
    public Integer progresoLogro(Long idEstudiante) throws Exception {
        if(!estudianteDAO.existsById(idEstudiante)){
            throw new Exception("El estudiante con ese id no existe");
        }
        return logroDAO.progresoLogros(idEstudiante);
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
        if(logro.getFechaCreacion()==null) {
            throw new Exception("Debe ingresar una fecha de creación.");
        }
    }

    private void validacionesActualizar(Logro logro) throws Exception{

        if(logro.getIdLogro() == null){
            throw new Exception("Debe ingresar el id del logro que desea actualizar.");
        }
        if(!logroDAO.existsById(logro.getIdLogro())){
            throw new Exception("No se encontró un logro con ese id.");
        }
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
        Date fechaActual = new Date();
        if(logro.getUsuarioModificador()==null || logro.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(logro.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.");
        }
        if(logro.getFechaModificacion()==null ){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        if(logro.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
