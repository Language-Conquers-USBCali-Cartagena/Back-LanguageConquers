package com.example.demo.service.serviceImpl;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.CursoEstudianteDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.model.CursoEstudiante;
import com.example.demo.service.CursoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class CursoEstudianteServiceImpl implements CursoEstudianteService {

    //Todo: falta metodos del crud y sus validaciones
    @Autowired
    CursoEstudianteDAO cursoEstudianteDAO;

    @Autowired
    EstudianteDAO estudianteDAO;

    @Autowired
    CursoDAO cursoDAO;
    @Override
    public String crearCursoEstudiante(CursoEstudiante cursoEstudiante) throws Exception {
        validacionesCursoEstudiante(cursoEstudiante);
        cursoEstudianteDAO.save(cursoEstudiante);
        return "Se creo exitosamente el curso estudiante";
    }

    @Override
    public List<CursoEstudiante> listarCursoEstudiante() throws Exception {
        cursoEstudianteDAO.findAll();
        return null;
    }

    private void validacionesCursoEstudiante(CursoEstudiante cursoEstudiante) throws Exception{

        if(!estudianteDAO.existsById(cursoEstudiante.getEstudiante().getIdEstudiante())){
            throw new Exception("El estudiante asignado no se encuentra en la base de datos");
        }
        if(!cursoDAO.existsById(cursoEstudiante.getCurso().getIdCurso())){
            throw new Exception("El curros asignado no existe en la base de datos");
        }
    }
}
