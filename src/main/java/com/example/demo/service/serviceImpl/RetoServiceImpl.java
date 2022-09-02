package com.example.demo.service.serviceImpl;

import com.example.demo.dao.RetoDAO;
import com.example.demo.model.Reto;
import com.example.demo.service.RetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class RetoServiceImpl implements RetoService {

    @Autowired
    RetoDAO retoDAO;

    @Override
    public List<Reto> listReto() throws Exception{

        return retoDAO.findAll();
    }

    @Override
    public String crearreto(Reto reto) throws Exception {
        try{
            retoDAO.save(reto);
            return "Se creo correctamente el reto";
        }catch (Exception e){
            throw new Exception("EL reto no se pudo crear");
        }
    }
}
