package com.example.demo.service.serviceImpl;

import com.example.demo.dao.MonedasDAO;
import com.example.demo.model.Monedas;
import com.example.demo.service.MonedasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class MonedasServiceImpl implements MonedasService {
    //TODO: TERMINAR DE CREAR LOS SERVICIOS BASICOS COMO EDITAR Y ELIMINAR
    @Autowired
    MonedasDAO monedasDAO;


    @Override
    public List<Monedas> findAlList() throws Exception {
        return monedasDAO.findAll();
    }

    @Override
    public String crarMonedas(Monedas monedas) throws Exception {
        try{
            monedasDAO.save(monedas);
            return "Se creo correctamente las monedas";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
