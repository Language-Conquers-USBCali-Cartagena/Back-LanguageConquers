package com.example.demo.service.serviceImpl;

import com.example.demo.dao.LogroDAO;
import com.example.demo.model.Logro;
import com.example.demo.model.dto.LogroDTO;
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
            throw new Exception("No hay logros disponibles.");
        }
        return logros;
    }

    @Override
    public Page<Logro> pageLogros(Pageable pageable) throws Exception {
        Page<Logro> logros= logroDAO.findAll(pageable);
        if(logros.isEmpty()){
            throw new Exception("No hay logros disponibles.");
        }
        return logros;
    }

    @Override
    public String registrar(Logro logro) throws Exception {
        validaciones(logro);
        logroDAO.save(logro);
        return "Se guardo exitosamente el logro.";
    }

    @Override
    public String eliminar(Long idLogro) throws Exception {
        if(idLogro == null){
            throw new Exception("Se debe ingresar el id del logro.");
        }
        if(!logroDAO.existsById(idLogro)){
            throw new Exception("El id logro no existe.");
        }
        logroDAO.deleteById(idLogro);
        return "El logro se elimino exitosamente.";
    }

    @Override
    public String actualizar(LogroDTO logroDTO) throws Exception {
        Logro logro = null;
        //Todo: Faltan validaciones
        logro = logroDAO.findById(logroDTO.getIdLogro()).orElse(null);
        logro.setNombre(logroDTO.getNombre());
        logro.setImagen(logroDTO.getImagen());
        logro.setDescripcion(logroDTO.getDescripcion());
        logro.setCategoria(logroDTO.getCategoria());//revisar si se quita
        logro.setUsuarioCreador(logroDTO.getUsuarioCreador());
        logro.setFechaCreacion(logroDTO.getFechaCreacion());
        logro.setUsuarioModificador(logroDTO.getUsuarioModificador());
        logroDAO.save(logro);
        return "Se actualizo correctamente el logro.";
    }

    @Override
    public Logro findById(Long idLogro) throws Exception {
        if(idLogro == null){
            throw new Exception("Se debe ingresar el id del logro.");
        }
        if(!logroDAO.existsById(idLogro)){
            throw new Exception("El id logro no existe.");
        }
        return logroDAO.findById(idLogro).get();
    }


    private void validaciones(Logro logro) throws Exception {
        //Todo: revisar este metodo no estan todas las validaciones
        if(logro.getCategoria().equals(null)){
            throw new Exception("Debe ingresar una categoría.");
        }
        if(logro.getDescripcion().equals(null)){
            throw new Exception("Debe ingresar una descripción.");
        }
        if(logro.getImagen().equals(null)){
            throw new Exception("Debe ingresar una imagen.");
        }
        if(logro.getNombre().equals(null)){
            throw new Exception("Debe ingresar el nombre del logro.");
        }

    }
}
