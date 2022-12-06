package com.example.demo.service.serviceImpl;

import com.example.demo.dao.LogroDAO;
import com.example.demo.model.Logro;
import com.example.demo.service.LogroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class LogroServiceImpl implements LogroService {

    @Autowired
    LogroDAO logroDAO;

    @Override
    public List<Logro> listarLogros() throws Exception {
        List<Logro> logros = logroDAO.findAll();
        if(logros.isEmpty()){
            throw new Exception("No hay logros disponibles");
        }
        return logros;
    }

    @Override
    public Page<Logro> pageLogros(Pageable pageable) throws Exception {
        Page<Logro> logros= logroDAO.findAll(pageable);
        if(logros.isEmpty()){
            throw new Exception("No hay logros disponibles");
        }
        return logros;
    }

    @Override
    public String save(Logro logro) throws Exception {
        validaciones(logro);
        logroDAO.save(logro);
        return "Se guardo exitosamente el logro";
    }

    @Override
    public String delete(Long idLogro) throws Exception {
        if(!logroDAO.existsById(idLogro)){
            throw new Exception("El id logro no existe en la bd");
        }
        logroDAO.deleteById(idLogro);
        return "El logro se elimino exitosamente";
    }

    @Override
    public String update(Logro logro) throws Exception {
        validaciones(logro);
        logroDAO.save(logro);
        return "Se actualizo correctamente el logeo";
    }

    private void validaciones(Logro logro) throws Exception {
        if(logro.getCategoria().equals(null)){
            throw new Exception("Debe ingresar una categoria");
        }
        if(logro.getDescripcion().equals(null)){
            throw new Exception("Debe ingresar una descripcion");
        }
        if(logro.getImagen().equals(null)){
            throw new Exception("Debe ingresar una imagen");
        }
        if(logro.getNombre().equals(null)){
            throw new Exception("Debe ingresar el nombre del logro");
        }

    }
}
