package com.example.demo.service.serviceImpl;

import com.example.demo.dao.GeneroDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.ProfesorDTO;
import com.example.demo.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    private ProfesorDAO profesorDAO;

    @Autowired
    private GeneroDAO generoDAO;

    @Override
    public String registarProfesor(ProfesorDTO profesorDTO) {
        try{
            Profesor profesor = new Profesor();
            profesor.setNombre(profesorDTO.getNombre());
            profesor.setApellido(profesorDTO.getApellido());
            profesor.setCorreo(profesorDTO.getCorreo());
            profesor.setFoto(profesorDTO.getFoto());
            profesor.setUsuarioCreador(profesorDTO.getUsuarioCreador());
            profesor.setFechaCreacion(profesorDTO.getFechaCreacion());
            profesor.setGenero(generoDAO.findById(profesorDTO.getIdGenero()).get());
            profesorDAO.save(profesor);
            return "Se creo exitosamente el profesor";
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
