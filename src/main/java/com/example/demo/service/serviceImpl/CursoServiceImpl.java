package com.example.demo.service.serviceImpl;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    CursoDAO cursoDAO;

    @Autowired
    EstadoDAO estadoDAO;

    @Autowired
    ProfesorDAO profesorDAO;
    @Override
    public String crearCurso(Curso curso) throws Exception {
        validaciones(curso);
        cursoDAO.save(curso);
        return "Se creo existosamente el curso";
    }

    public void validaciones(Curso curso) throws Exception{
        if(!estadoDAO.existsById(curso.getEstado().getIdEstado())){
            throw new Exception("El estado que ingreso no es valido");
        }
        if(!profesorDAO.existsById(curso.getProfesor().getIdProfesor())){
            throw new Exception("El profesor que ingreso no es valido");
        }
    }
}
