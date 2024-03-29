package com.example.demo.service.serviceImpl;

import com.example.demo.dao.*;
import com.example.demo.model.*;
import com.example.demo.model.dto.MisionDTO;
import com.example.demo.service.*;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class MisionServiceImpl implements MisionService {


    @Autowired
    MisionDAO misionDAO;

    @Autowired
    CursoDAO cursoDAO;

    @Autowired
    RetoDAO retoDAO;

    @Autowired
    MisionEstudianteDAO misionEstudianteDAO;


    @Override
    public List<Mision> ListarMisiones() throws Exception {
        return misionDAO.findAll();
    }

    @Override
    public String registrar(Mision mision) throws Exception {
        validacionesCrear(mision);
        misionDAO.save(mision);
        return "Se creo exitosamente la misión.";
    }

    @Override
    public String actualizar(Mision mision) throws Exception {
        validacionActualizar(mision);
        misionDAO.save(mision);
        return "Se actualizo la misión correctamente.";
    }

    @Override
    public String eliminar(Long idMision) throws Exception {
        if(idMision == null){
            throw new Exception("Se debe ingresar el id de la misión.");
        }
        if(!misionDAO.existsById(idMision)){
            throw new Exception("La misión con id: "+ idMision + " no existe.");
        }
        if(!misionEstudianteDAO.findByIdMision(idMision).isEmpty()){
            throw new Exception("No se puede eliminar la misión porque esta siendo utilizada en un reto estudiante.");
        }
        if(!retoDAO.findByIdMision(idMision).isEmpty()){
            throw new Exception("No se puede eliminar la misión porque esta siendo utilizada en un reto.");
        }
        misionDAO.deleteById(idMision);
        return "La misión se ha eliminado exitosamente.";
    }

    @Override
    public Mision findById(Long idMision) throws Exception {
        if(idMision == null){
            throw new Exception("Debe ingresar el id de una misión");
        }
        if(!misionDAO.existsById(idMision)){
            throw new Exception("La misión con id: " + idMision + " no existe.");
        }
        return misionDAO.findById(idMision).get();
    }

    private void validacionesCrear(Mision mision) throws Exception {
        if(mision.getNombre() == null || mision.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre de la misión.");
        }
        if(Validaciones.isStringLenght(mision.getNombre(), 50)){
            throw new Exception("El nombre de la misión es muy largo, solo se aceptan 50 caracteres.");
        }
        //TODO: FALTA VALIDACIÓN DE IMAGEN
        if(Validaciones.isStringLenght(mision.getImagen(), 250)){
            throw new Exception("La URL de la imagen es muy largo, solo se aceptan 250 caracteres.");
        }
        if(mision.getCurso().getIdCurso() == null){
            throw new Exception("Se debe ingresar un id del curso.");
        }
        if(mision.getCurso().getIdCurso()<0){
            throw new Exception("Se debe ingresar un id del curso válido.");
        }
        if(cursoDAO.findById(mision.getCurso().getIdCurso()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id del curso que exista.");
        }


        Date fechaActual = new Date();
        if(mision.getUsuarioCreador()==null || mision.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(mision.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(mision.getFechaCreacion()==null){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
    }
    private void validacionActualizar(Mision mision)throws Exception{
        if(mision.getIdMision() == null){
            throw new Exception("Debe ingresar un id misión que desea actualizar.");
        }
        if(!misionDAO.existsById(mision.getIdMision())){
            throw new Exception("No se encontró la misión con ese id.");
        }

        if(!cursoDAO.existsById(mision.getCurso().getIdCurso())){
            throw new Exception("No existe un curso con ese id.");
        }

        if(mision.getNombre() == null || mision.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre de la misión.");
        }
        if(Validaciones.isStringLenght(mision.getNombre(), 50)){
            throw new Exception("El nombre de la misión es muy largo, solo se aceptan 50 caracteres.");
        }
        //TODO: FALTA VALIDACIÓN DE IMAGEN
        if(Validaciones.isStringLenght(mision.getImagen(), 250)){
            throw new Exception("La URL de la imagen es muy largo, solo se aceptan 250 caracteres.");
        }
        if(mision.getCurso().getIdCurso() == null){
            throw new Exception("Se debe ingresar un id del curso.");
        }
        if(mision.getCurso().getIdCurso()<0){
            throw new Exception("Se debe ingresar un id del curso válido.");
        }
        if(cursoDAO.findById(mision.getCurso().getIdCurso()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id del curso que exista.");
        }

        Date fechaActual = new Date();
        if(mision.getUsuarioModificador()==null || mision.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(mision.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(mision.getFechaModificacion()==null ){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
        if(mision.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
