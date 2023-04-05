package com.example.demo.service.serviceImpl;

import com.example.demo.dao.*;
import com.example.demo.mapper.LogroEstudianteMapper;
import com.example.demo.model.*;
import com.example.demo.model.dto.LogroEstudianteDTO;
import com.example.demo.service.LogroEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class LogroEstudianteServiceImpl implements LogroEstudianteService {


    @Autowired
    LogroEstudianteDAO logroEstudianteDAO;

    @Autowired
    EstudianteDAO estudianteDAO;

    @Autowired
    LogroDAO logroDAO;

    @Autowired
    LogroEstudianteMapper logroEstudianteMapper;

    @Autowired
    ArticulosAdquiridosDAO articulosAdquiridosDAO;

    @Autowired
    RetoEstudianteDAO retoEstudianteDAO;
    @Override
    public String save(LogroEstudiante logroEstudiante) throws Exception {
        validaciones(logroEstudiante);
        logroEstudianteDAO.save(logroEstudiante);
        return "Se guardo exitosamente el logro estudiante.";
    }
    public String saveByDTO(LogroEstudianteDTO logroEstudianteDTO) throws Exception {
        LogroEstudiante logroEstudiante = logroEstudianteMapper.toEntity(logroEstudianteDTO);
        save(logroEstudiante);
        return "Se registro exitosamente el logro al estudiante";
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

    @Override
    public LogroEstudiante findByEstudianteAndLogro(Long idEstudiante, Long idLogro) throws Exception {
        return logroEstudianteDAO.findByEstudianteAndLogro(idEstudiante, idLogro);

    }

    @Override
    public String logroArticulos(Long idEstudiante) throws Exception {
        List<ArticulosAdquiridos> articulosAdquiridos = articulosAdquiridosDAO.findByIdEstudiante(idEstudiante);
        LogroEstudiante logroEstudiante = findByEstudianteAndLogro(idEstudiante, 1L);
        if(!articulosAdquiridos.isEmpty() && logroEstudiante != null){
            return null;
        }
        LogroEstudianteDTO logroEstudianteDTO = new LogroEstudianteDTO();
        logroEstudianteDTO.setIdEstudiante(idEstudiante);
        logroEstudianteDTO.setIdLogro(1L);
        logroEstudianteDTO.setFechaCreacion(new Date());
        logroEstudianteDTO.setUsuarioCreador("admin");
        saveByDTO(logroEstudianteDTO);
        return "Has conseguido el logro 'Comprador'";
    }

    @Override
    public String logroAhorrador(Long idEstudiante) throws Exception {
        Estudiante estudiante = estudianteDAO.findById(idEstudiante).get();
        if(estudiante.getMonedasObtenidas() >= 800){
            LogroEstudianteDTO logroEstudianteDTO = new LogroEstudianteDTO();
            logroEstudianteDTO.setIdEstudiante(idEstudiante);
            logroEstudianteDTO.setIdLogro(11L);
            logroEstudianteDTO.setFechaCreacion(new Date());
            logroEstudianteDTO.setUsuarioCreador("admin");
            saveByDTO(logroEstudianteDTO);
            return "Has conseguido el logro 'Ahorrador'";
        }
        return null;
    }
    @Override
    public String logroPerfeccionista(Long idEstudiante, Long idReto) throws Exception {
        RetoEstudiante retoEstudiante = retoEstudianteDAO.findByIdRetoAndIdEstuduante(idReto, idEstudiante);
        retoEstudiante.getIntentos();
        if(retoEstudiante.getIntentos() == 0){
            return "Has conseguido el logro 'Perfeccionista'";
        }
        return null;
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
