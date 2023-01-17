package com.example.demo.service.serviceImpl;

import com.example.demo.dao.PalabrasReservadasDAO;
import com.example.demo.model.PalabrasReservadas;
import com.example.demo.service.PalabrasReservadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class PalabrasReservadasServiceImpl implements PalabrasReservadasService {

    @Autowired
    PalabrasReservadasDAO palabrasReservadasDAO;


    @Override
    public List<PalabrasReservadas> findAll() throws Exception {
        return palabrasReservadasDAO.findAll();
    }

    @Override
    public String crearPalabraResevada(PalabrasReservadas palabrasReservadas) throws Exception {
        try{
            palabrasReservadasDAO.save(palabrasReservadas);
            return "Se ha creado correctamente la palabra reservada.";
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}
