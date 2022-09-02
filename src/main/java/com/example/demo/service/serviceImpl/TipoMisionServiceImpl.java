package com.example.demo.service.serviceImpl;

import com.example.demo.dao.TipoMisionDAO;
import com.example.demo.model.TipoMision;
import com.example.demo.service.TipoMisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class TipoMisionServiceImpl implements TipoMisionService {

    @Autowired
    TipoMisionDAO tipoMisionDAO;


    @Override
    public List<TipoMision> findAll() throws Exception {
        return tipoMisionDAO.findAll();
    }

    @Override
    public String crearTipoMision(TipoMision tipoMision) throws Exception {
        try{
            tipoMisionDAO.save(tipoMision);
            return "El tipo mision se creo exitosamente";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
