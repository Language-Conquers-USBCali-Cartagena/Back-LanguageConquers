package com.example.demo.service.serviceImpl;

import com.example.demo.dao.MisionDAO;
import com.example.demo.model.Mision;
import com.example.demo.service.MisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class MisionServiceImpl implements MisionService {

    //TODO: TERMINAR DE CREAR LOS SERVICIOS BASICOS COMO EDITAR Y ELIMINAR
    @Autowired
    MisionDAO misionDAO;
    @Override
    public List<Mision> ListarMisiones() throws Exception {
        return misionDAO.findAll();
    }

    @Override
    public String crearMision(Mision mision) throws Exception {
        try{
            misionDAO.save(mision);

            return "Se creo la mision";

        }catch (Exception e){
            throw  new Exception("No se pudo crear la mision");
        }
    }
}
