package com.example.demo.service.serviceImpl;

import com.example.demo.dao.TipoMisionDAO;
import com.example.demo.model.TipoMision;
import com.example.demo.model.dto.TipoMisionDTO;
import com.example.demo.service.TipoMisionService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class TipoMisionServiceImpl implements TipoMisionService {

    //TODO: TERMINAR DE CREAR LOS SERVICIOS BASICOS COMO EDITAR Y ELIMINAR
    @Autowired
    TipoMisionDAO tipoMisionDAO;


    @Override
    public List<TipoMision> findAll() throws Exception {
        return tipoMisionDAO.findAll();
    }

    @Override
    public String registrar(TipoMision tipoMision) throws Exception {
        try{
            validacionesCrear(tipoMision);
            tipoMisionDAO.save(tipoMision);
            return "El tipo misión se creo exitosamente.";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String actualizar(TipoMisionDTO tipoMisionDTO) throws Exception {
        TipoMision tipoMision = null;
        validacionesActualizar(tipoMisionDTO);
        tipoMision = tipoMisionDAO.findById(tipoMisionDTO.getIdTipoMision()).orElse(null);
        tipoMision.setDescripcion(tipoMisionDTO.getDescripcion());
        tipoMision.setUsuarioModificador(tipoMisionDTO.getUsuarioModificador());
        tipoMision.setFechaModificacion(tipoMisionDTO.getFechaModificacion());
        tipoMisionDAO.save(tipoMision);
        return "Se actualizo correctamente el tipo misión.";
    }

    @Override
    public String eliminar(Long idTipoMision) throws Exception {
        if(idTipoMision == null){
            throw new Exception("Se debe ingresar el id del tipo misión.");
        }
        if(!tipoMisionDAO.existsById(idTipoMision)){
            throw new Exception("No se encontró el tipo misión con el id: " + idTipoMision +".");
        }
        tipoMisionDAO.deleteById(idTipoMision);
        return "Se elimino correctamente el tipo misión.";
    }

    @Override
    public TipoMision findById(Long idTipoMision) throws Exception {
        if(idTipoMision == null){
            throw new Exception("Se debe ingresar el id del tipo misión.");
        }
        if(!tipoMisionDAO.existsById(idTipoMision)){
            throw new Exception("No se encontró el tipo misión con el id: " + idTipoMision+".");
        }
        return tipoMisionDAO.findById(idTipoMision).get();
    }

    private void validacionesCrear(TipoMision tipoMision)throws Exception{
        if(tipoMision.getDescripcion() == null|| tipoMision.getDescripcion().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción para la misión.");
        }
        if(Validaciones.isStringLenght(tipoMision.getDescripcion(),200)){
            throw new Exception("La descripción es muy larga, solo se permiten 200 caracteres.");
        }
        if(tipoMision.getUsuarioCreador()==null || tipoMision.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(tipoMision.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(tipoMision.getFechaCreacion()==null || tipoMision.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
    }

    private void validacionesActualizar(TipoMisionDTO tipoMisionDTO)throws Exception{
        if(tipoMisionDTO.getIdTipoMision()== null || tipoMisionDTO.getIdTipoMision().toString().equals("")){
            throw new Exception("Debe ingresar el id del tipo misión que desea actualizar");
        }
        if(tipoMisionDTO.getIdTipoMision()<0){
            throw new Exception("Debe ingresar un id de tipo misión válido.");
        }
        if(!tipoMisionDAO.existsById(tipoMisionDTO.getIdTipoMision())){
            throw new Exception("No se encontró el tipo misión con ese id.");
        }
        if(tipoMisionDTO.getDescripcion() == null|| tipoMisionDTO.getDescripcion().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción para la misión.");
        }
        if(Validaciones.isStringLenght(tipoMisionDTO.getDescripcion(),200)){
            throw new Exception("La descripción es muy larga, solo se permiten 200 caracteres.");
        }
        if(tipoMisionDTO.getUsuarioModificador()==null || tipoMisionDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(tipoMisionDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.");
        }
        if(tipoMisionDTO.getFechaModificacion()==null || tipoMisionDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        Date fechaActual = new Date();
        if(tipoMisionDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
