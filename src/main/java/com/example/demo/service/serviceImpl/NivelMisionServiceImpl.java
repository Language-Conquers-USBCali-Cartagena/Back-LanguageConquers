package com.example.demo.service.serviceImpl;

import com.example.demo.dao.NivelMisionDAO;
import com.example.demo.model.NivelMision;
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
    public String crearNivelMision(NivelMision nivelMision) throws Exception {

        try{
            nivelMisionDAO.save(nivelMision);
            return "Se pudo crear correctamente el nivel mision";
        }catch (Exception e){
            throw new Exception("No se puede crear el nivel");
        }
    }
}
