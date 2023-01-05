package com.example.demo.service.serviceImpl;

import com.example.demo.dao.NivelMisionDAO;
import com.example.demo.model.NivelMision;
import com.example.demo.model.dto.NivelMisionDTO;
import com.example.demo.service.NivelMisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class NivelMisionServiceImpl implements NivelMisionService {
    //TODO: TERMINAR DE CREAR LOS SERVICIOS BASICOS COMO EDITAR Y ELIMINAR
    @Autowired
    NivelMisionDAO nivelMisionDAO;


    @Override
    public List<NivelMision> findAll() throws Exception {
        return nivelMisionDAO.findAll();
    }

    @Override
    public String Registrar(NivelMision nivelMision) throws Exception {
        //Todo: faltan validaciones
        try{
            nivelMisionDAO.save(nivelMision);
            return "Se pudo crear correctamente el nivel mision";
        }catch (Exception e){
            throw new Exception("No se puede crear el nivel");
        }
    }

    @Override
    public String actualizar(NivelMisionDTO nivelMisionDTO) throws Exception {
        NivelMision nivelMision = null;
        //Todo: faltan validaciones
        nivelMision = nivelMisionDAO.findById(nivelMisionDTO.getIdNivelMision()).orElse(null);
        nivelMision.setNombre(nivelMisionDTO.getNombre());
        nivelMision.setPuntajeMinimo(nivelMisionDTO.getPuntajeMinimo());
        nivelMision.setUsuarioCreador(nivelMisionDTO.getUsuarioCreador());
        nivelMision.setFechaCreacion(nivelMisionDTO.getFechaCreacion());
        nivelMision.setUsuarioModificador(nivelMisionDTO.getUsuarioModificador());
        nivelMisionDAO.save(nivelMision);
        return "Se actualizo correctamente el nivelMision";
    }

    @Override
    public String eliminar(Long idNivelMision) throws Exception {
        if(idNivelMision == null){
            throw new Exception("Se debe ingresar el id del ");
        }
        if(!nivelMisionDAO.existsById(idNivelMision)){
            throw new Exception("No se encontro el nivelMision con el id: " + idNivelMision);
        }
        nivelMisionDAO.deleteById(idNivelMision);
        return "Se elimino el nivel mision correctamente";
    }

    @Override
    public NivelMision findById(Long idNivelMision) throws Exception {
        if(idNivelMision == null){
            throw new Exception("Se debe ingresar el id del ");
        }
        if(!nivelMisionDAO.existsById(idNivelMision)){
            throw new Exception("No se encontro el nivelMision con el id: " + idNivelMision);
        }
        return nivelMisionDAO.findById(idNivelMision).get();
    }
}
