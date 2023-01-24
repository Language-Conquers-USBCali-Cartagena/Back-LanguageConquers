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

    //TODO: TERMINAR DE CREAR LOS SERVICIOS BASICOS COMO EDITAR Y ELIMINAR
    @Autowired
    MisionDAO misionDAO;

    @Autowired
    NivelMisionDAO nivelMisionDAO;

    @Autowired
    CursoDAO cursoDAO;

    @Autowired
    TipoMisionDAO tipoMisionDAO;

    @Autowired
    MonedasDAO monedasDAO;
    @Override
    public List<Mision> ListarMisiones() throws Exception {
        return misionDAO.findAll();
    }

    @Override
    public String registrar(Mision mision) throws Exception {
        try{
            validacionesCrear(mision);
            misionDAO.save(mision);
            return "Se creo exitosamente la misión.";
        }catch (Exception e){
            throw  new Exception("No se pudo crear la misión. " + e.getMessage() );
        }
    }

    @Override
    public String actualizar(MisionDTO misionDTO) throws Exception {
        Mision mision = null;
        validacionActualizar(misionDTO);
        mision = misionDAO.findById(misionDTO.getIdMision()).orElse(null);
        mision.setNombre(misionDTO.getNombre());
        mision.setNivelMision(nivelMisionDAO.findById(misionDTO.getIdMision()).orElse(null));
        mision.setCurso(cursoDAO.findById(misionDTO.getIdCurso()).orElse(null));
        mision.setTipoMision(tipoMisionDAO.findById(misionDTO.getIdTipoMision()).orElse(null));
        mision.setMonedas(monedasDAO.findById(misionDTO.getIdMonedas()).orElse(null));
        mision.setUsuarioCreador(misionDTO.getUsuarioCreador());
        mision.setFechaCreacion(misionDTO.getFechaCreacion());
        misionDTO.setUsuarioModificador(misionDTO.getUsuarioModificador());
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
        //Todo: falta validacion de reto y mision_estudiante
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
        if(mision.getImagen() == null || mision.getImagen().trim().equals("")){
            throw new Exception("Se debe ingresar una imagen de la misión");
        }
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
        if(mision.getMonedas().getIdMoneda() == null){
            throw new Exception("Se debe ingresar un id de la moneda.");
        }
        if(mision.getMonedas().getIdMoneda()<0){
            throw new Exception("Se debe ingresar un id de la moneda válido.");
        }
        if(monedasDAO.findById(mision.getMonedas().getIdMoneda()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id de la moneda que exista.");
        }
        if(mision.getTipoMision().getIdTipoMision() == null){
            throw new Exception("Se debe ingresar un id del tipo misión.");
        }
        if(mision.getTipoMision().getIdTipoMision()<0){
            throw new Exception("Se debe ingresar un id del tipo misión válido.");
        }
        if(tipoMisionDAO.findById(mision.getTipoMision().getIdTipoMision()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id del tipo misión que exista.");
        }
        if(mision.getNivelMision().getIdNivelMision() == null){
            throw new Exception("Se debe ingresar un id del nivel misión.");
        }
        if(mision.getNivelMision().getIdNivelMision()<0){
            throw new Exception("Se debe ingresar un id del nivel misión válido.");
        }
        if(nivelMisionDAO.findById(mision.getNivelMision().getIdNivelMision()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id del nivel misión que exista.");
        }
        Date fechaActual = new Date();
        if(mision.getUsuarioCreador()==null || mision.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(mision.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(mision.getFechaCreacion()==null || mision.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
    }
    private void validacionActualizar(MisionDTO misionDTO)throws Exception{
        if(misionDTO.getIdMision() == null){
            throw new Exception("Debe ingresar un id misión que desea actualizar.");
        }
        if(!misionDAO.existsById(misionDTO.getIdMision())){
            throw new Exception("No se encontró la misión con ese id.");
        }
        if(!tipoMisionDAO.existsById(misionDTO.getIdTipoMision())){
            throw new Exception("No existe un tipo misión con ese id.");
        }
        if(!nivelMisionDAO.existsById(misionDTO.getIdNivelMision())){
            throw new Exception("No existe un nivel misión con ese id.");
        }
        if(!cursoDAO.existsById(misionDTO.getIdCurso())){
            throw new Exception("No existe un curso con ese id.");
        }
        if(!monedasDAO.existsById(misionDTO.getIdMonedas())){
            throw new Exception("No existe una moneda con ese id.");
        }
        if(misionDTO.getNombre() == null || misionDTO.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre de la misión.");
        }
        if(Validaciones.isStringLenght(misionDTO.getNombre(), 50)){
            throw new Exception("El nombre de la misión es muy largo, solo se aceptan 50 caracteres.");
        }
        if(misionDTO.getImagen() == null || misionDTO.getImagen().trim().equals("")){
            throw new Exception("Se debe ingresar una imagen de la misión");
        }
        if(Validaciones.isStringLenght(misionDTO.getImagen(), 250)){
            throw new Exception("La URL de la imagen es muy largo, solo se aceptan 250 caracteres.");
        }
        if(misionDTO.getIdCurso() == null){
            throw new Exception("Se debe ingresar un id del curso.");
        }
        if(misionDTO.getIdCurso()<0){
            throw new Exception("Se debe ingresar un id del curso válido.");
        }
        if(cursoDAO.findById(misionDTO.getIdCurso()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id del curso que exista.");
        }
        if(misionDTO.getIdMonedas() == null){
            throw new Exception("Se debe ingresar un id de la moneda.");
        }
        if(misionDTO.getIdMonedas()<0){
            throw new Exception("Se debe ingresar un id de la moneda válido.");
        }
        if(monedasDAO.findById(misionDTO.getIdMonedas()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id de la moneda que exista.");
        }
        if(misionDTO.getIdTipoMision()== null){
            throw new Exception("Se debe ingresar un id del tipo misión.");
        }
        if(misionDTO.getIdTipoMision()<0){
            throw new Exception("Se debe ingresar un id del tipo misión válido.");
        }
        if(tipoMisionDAO.findById(misionDTO.getIdTipoMision()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id del tipo misión que exista.");
        }
        if(misionDTO.getIdNivelMision() == null){
            throw new Exception("Se debe ingresar un id del nivel misión.");
        }
        if(misionDTO.getIdNivelMision()<0){
            throw new Exception("Se debe ingresar un id del nivel misión válido.");
        }
        if(nivelMisionDAO.findById(misionDTO.getIdNivelMision()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id del nivel misión que exista.");
        }
        Date fechaActual = new Date();
        if(misionDTO.getUsuarioModificador()==null || misionDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(misionDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(misionDTO.getFechaModificacion()==null || misionDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
        if(misionDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
