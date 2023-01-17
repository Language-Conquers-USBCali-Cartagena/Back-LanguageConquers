package com.example.demo.service.serviceImpl;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.LogroDAO;
import com.example.demo.dao.LogroEstudianteDAO;
import com.example.demo.model.Logro;
import com.example.demo.model.LogroEstudiante;
import com.example.demo.service.LogroEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class LogroEstudianteServiceImpl implements LogroEstudianteService {
    //TODO:REALIZAR SERVICIO PARA OBTENER LOS LOGROS QUE HA DESBLOQUEADO EL ESTUDIANTE Y LOS QUE LE FALTAN POR DESBLOQUEAR

    @Autowired
    LogroEstudianteDAO logroEstudianteDAO;

    @Autowired
    EstudianteDAO estudianteDAO;

    @Autowired
    LogroDAO logroDAO;

    @Override
    public String save(LogroEstudiante logroEstudiante) throws Exception {
        validaciones(logroEstudiante);
        logroEstudianteDAO.save(logroEstudiante);
        return "Se guardo exitosamente el logro estudiante.";
    }

    @Override
    public String delete(Long idLogroEstudiante) throws Exception {
        if (!logroEstudianteDAO.existsById(idLogroEstudiante)) {
            throw new Exception("El id del logro estudiante no existe.");
        }
        logroEstudianteDAO.deleteById(idLogroEstudiante);
        return "El logro estudiante fue eliminado exitosamente.";
    }
    @Override
    public String actualizar(LogroEstudiante logroEstudiante) throws Exception {
        validaciones(logroEstudiante);
        logroEstudianteDAO.save(logroEstudiante);
        return "Se actualizo correctamente el logro estudiante.";
    }

    @Override
    public List<LogroEstudiante> findAllByIdEstudiante(Long idEstudiante) throws Exception {
        List<LogroEstudiante> logroEstudiantes = logroEstudianteDAO.findByEstudiante(estudianteDAO.findById(idEstudiante).get());
        if(logroEstudiantes.isEmpty()){
            throw new Exception("El estudiante aun no ha obtenido un logro.");
        }
        return logroEstudiantes;
    }

    private void validaciones(LogroEstudiante logroEstudiante) throws Exception{
        if(!estudianteDAO.existsById(logroEstudiante.getEstudiante().getIdEstudiante())){
            throw new Exception("El id estudiante no es válido.");
        }
        if(!logroDAO.existsById(logroEstudiante.getLogro().getIdLogro())){
            throw new Exception("El id logro no es válido.");
        }
    }
}
