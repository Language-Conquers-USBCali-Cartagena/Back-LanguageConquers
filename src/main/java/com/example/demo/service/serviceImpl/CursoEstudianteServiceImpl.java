package com.example.demo.service.serviceImpl;

import com.example.demo.dao.CursoEstudianteDAO;
import com.example.demo.model.CursoEstudiante;
import com.example.demo.service.CursoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class CursoEstudianteServiceImpl implements CursoEstudianteService {
    @Autowired
    CursoEstudianteDAO cursoEstudianteDAO;
    @Override
    public String crearCursoEstudiante(CursoEstudiante cursoEstudiante) throws Exception {
//        if()
        cursoEstudianteDAO.save(cursoEstudiante);
        return "Se creo exitosamente el curso estudiante";
    }
}
