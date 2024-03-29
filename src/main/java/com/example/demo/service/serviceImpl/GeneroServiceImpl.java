package com.example.demo.service.serviceImpl;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.GeneroDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.model.Genero;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.GeneroService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroDAO generoDAO;

    @Autowired
    private EstudianteDAO estudianteDAO;

    @Autowired
    private ProfesorDAO profesorDAO;

    @Override
    public String registrar(Genero genero) throws Exception {
       validacionesCrear(genero);
        generoDAO.save(genero);
        return "Se creo el genero exitosamente.";
    }

    @Override
    public String actualizar(GeneroDTO generoDTO) throws Exception {
        Genero genero = null;
        validacionesActualizar(generoDTO);
        genero = generoDAO.findById(generoDTO.getIdGenero()).orElse(null);
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        generoDAO.save(genero);
        return "Se actualizo exitosamente el genero.";
    }

    @Override
    public String eliminar(Long idGenero) throws Exception {
        if(idGenero == null){
            throw new Exception("El id del genero es obligatorio.");
        }
        if(!generoDAO.existsById(idGenero)){
            throw new Exception("No se encontró el genero con ese id.");
        }
        if(!estudianteDAO.findByIdGenero(idGenero).isEmpty()){
            throw new Exception("No se puede eliminar el genero porque esta siendo utilizado por un estudiante.");
        }
        if(!profesorDAO.findByIdGenero(idGenero).isEmpty()){
            throw new Exception("No se puede eliminar el genero porque esta siendo utilizado por un profesor.");
        }
        generoDAO.deleteById(idGenero);
        return "Se elimino exitosamente el genero.";
    }

    @Override
    public Genero findById(Long idGenero) throws Exception {
        if(idGenero == null){
            throw new Exception("Debe ingresar el id de un genero.");
        }
        if(!generoDAO.existsById(idGenero)){
            throw new Exception("El genero con id: " + idGenero + " no existe.");
        }
        return generoDAO.findById(idGenero).get();
    }

    @Override
    public List<Genero> listar() {
        return generoDAO.findAll();
    }
    private void validacionesCrear(Genero genero)throws Exception{
        if(genero.getGenero() == null || genero.getGenero().trim().equals("")){
            throw new Exception("Debe ingresar un nombre de genero.");
        }
        if(Validaciones.isStringLenght(genero.getGenero(),50)){
            throw new Exception("El nombre del genero es muy largo.");
        }
        if(genero.getUsuarioCreador() == null
                || genero.getUsuarioCreador().trim().equals("")
                || Validaciones.isStringLenght(genero.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar un usuario creador del genero válido.");
        }
        if(genero.getFechaCreacion() == null){
            throw new Exception("Se debe ingresar una fecha de creación válida.");
        }
        Date fechaActual = new Date();
        if(genero.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
    private void validacionesActualizar(GeneroDTO generoDTO)throws Exception{
        if(generoDTO.getIdGenero() == null){
            throw new Exception("Debe ingresar el id del genero que desea actualizar.");
        }
        if(!generoDAO.existsById(generoDTO.getIdGenero())){
            throw new Exception("No existe el genero con ese id.");
        }
        if(generoDTO.getGenero() == null || generoDTO.getGenero().trim().equals("")){
            throw new Exception("Debe ingresar un nombre de genero.");
        }
        if(Validaciones.isStringLenght(generoDTO.getGenero(),50)){
            throw new Exception("El nombre del genero es muy largo.");
        }
        if(generoDTO.getUsuarioModificador() == null
                || generoDTO.getUsuarioModificador().trim().equals("")
                || Validaciones.isStringLenght(generoDTO.getUsuarioModificador(),50)){
            throw new Exception("Se debe ingresar un usuario modificador del genero válido.");
        }
        if(generoDTO.getFechaModificacion() == null){
            throw new Exception("Se debe ingresar una fecha de modificación válida.");
        }
        Date fechaActual = new Date();
        if(generoDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
