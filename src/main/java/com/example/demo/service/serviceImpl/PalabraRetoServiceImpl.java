package com.example.demo.service.serviceImpl;

import com.example.demo.dao.PalabraRetoDAO;
import com.example.demo.dao.PalabrasReservadasDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.model.PalabraReto;
import com.example.demo.service.PalabraRetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class PalabraRetoServiceImpl implements PalabraRetoService {
    @Autowired
    PalabraRetoDAO palabraRetoDAO;

    @Autowired
    PalabrasReservadasDAO palabrasReservadasDAO;

    @Autowired
    RetoDAO retoDAO;

    @Override
    public List<PalabraReto> findAll() throws Exception {
        return palabraRetoDAO.findAll();
    }

    @Override
    public String crearPalabraReto(PalabraReto palabraReto) throws Exception {
        validaciones(palabraReto);
        palabraRetoDAO.save(palabraReto);
        return "Se creo el reto palabra exitosamente";
    }

    @Override
    public String editarPalabraReto(PalabraReto palabraReto) throws Exception {
        if(!palabraRetoDAO.existsById(palabraReto.getIdPalabraReto())){
            throw new Exception("El id de reto palabra no existe");
        }
        validaciones(palabraReto);
        palabraRetoDAO.save(palabraReto);
        return "Se actualizo el reto palabra exitosamente";
    }

    @Override
    public String eliminarPalabraReto(Long idPalabraReto) throws Exception {
        if(!palabraRetoDAO.existsById(idPalabraReto)){
            throw new Exception("El id de reto palabra no existe");
        }
        palabraRetoDAO.deleteById(idPalabraReto);
        return "Se elimino el reto palabra exitosamente";
    }

    @Override
    public List<PalabraReto> findbyIdReto(Long idReto) throws Exception {
        return palabraRetoDAO.findByIdReto(idReto);
    }

    private void validaciones (PalabraReto palabraReto) throws Exception{
        if(!retoDAO.existsById(palabraReto.getReto().getIdReto())){
            throw new Exception("El id del reto no existe");
        }
        if(!palabrasReservadasDAO.existsById(palabraReto.getPalabrasReservadas().getIdPalabraReservada())){
            throw new Exception("El id de la palabra reservada no existe");
        }
    }
}
