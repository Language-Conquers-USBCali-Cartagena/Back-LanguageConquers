package com.example.demo.service.serviceImpl;

import com.example.demo.dao.TipoMisionDAO;
import com.example.demo.model.TipoMision;
import com.example.demo.model.dto.TipoMisionDTO;
import com.example.demo.service.TipoMisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
        //Todo:Faltan validaciones
        try{
            tipoMisionDAO.save(tipoMision);
            return "El tipo mision se creo exitosamente";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String actualizar(TipoMisionDTO tipoMisionDTO) throws Exception {
        TipoMision tipoMision = null;
        //Todo: faltan validaciones
        tipoMision = tipoMisionDAO.findById(tipoMisionDTO.getIdTipoMision()).orElse(null);
        tipoMision.setDescripcion(tipoMisionDTO.getDescripcion());
        tipoMision.setUsuarioCreador(tipoMisionDTO.getUsuarioCreador());
        tipoMision.setFechaCreacion(tipoMisionDTO.getFechaCreacion());
        tipoMision.setUsuarioModificador(tipoMisionDTO.getUsuarioModificador());
        tipoMision.setFechaModificacion(tipoMisionDTO.getFechaModificacion());
        tipoMisionDAO.save(tipoMision);
        return "Se actualizo correctamente el tipoMision";
    }

    @Override
    public String eliminar(Long idTipoMision) throws Exception {
        if(idTipoMision == null){
            throw new Exception("Se debe ingresar el id del tipoMision");
        }
        if(!tipoMisionDAO.existsById(idTipoMision)){
            throw new Exception("No se encontro el tipoMision con el id: " + idTipoMision);
        }
        tipoMisionDAO.deleteById(idTipoMision);
        return "Se elimino correctamente el tipoMision";
    }

    @Override
    public TipoMision findById(Long idTipoMision) throws Exception {
        if(idTipoMision == null){
            throw new Exception("Se debe ingresar el id del tipoMision");
        }
        if(!tipoMisionDAO.existsById(idTipoMision)){
            throw new Exception("No se encontro el tipoMision con el id: " + idTipoMision);
        }
        return tipoMisionDAO.findById(idTipoMision).get();
    }
}
