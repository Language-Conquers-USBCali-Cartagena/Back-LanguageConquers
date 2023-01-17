package com.example.demo.service.serviceImpl;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.model.Curso;
import com.example.demo.model.Estado;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.service.EstadoService;
import com.example.demo.service.ProfesorService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class CursoServiceImpl implements CursoService {

    //TODO: faltan validaciones y metodos del crud
    @Autowired
    CursoDAO cursoDAO;

    @Autowired
    EstadoDAO estadoDAO;

    @Autowired
    ProfesorDAO profesorDAO;

    @Autowired
    EstadoService estadoService;

    @Autowired
    ProfesorService profesorService;
    @Override
    public String registrar(Curso curso) throws Exception {
        validaciones(curso);
        cursoDAO.save(curso);
        return "Se creo exitosamente el curso.";
    }

    @Override
    public String actualizar(CursoDTO cursoDTO) throws Exception {
        Curso curso = null;
        Estado idEstado = null;
        Profesor idProfesor = null;
        //Todo: Faltan validaciones
        curso = cursoDAO.findById(cursoDTO.getIdCurso()).orElse(null);
        curso.setNombre(cursoDTO.getNombre());
        curso.setPassword(cursoDTO.getPassword());
        curso.setCantidadEstudiantes(cursoDTO.getCantidadEstudiantes());
        curso.setInicioCurso(cursoDTO.getInicioCurso());
        curso.setFinCurso(cursoDTO.getFinCurso());
        curso.setProgreso(cursoDTO.getProgreso());
        idProfesor = profesorService.findById(cursoDTO.getIdProfesor());
        curso.setProfesor(idProfesor);
        idEstado = estadoService.findById(cursoDTO.getIdEstado());
        curso.setEstado(idEstado);
        cursoDAO.save(curso);
        return "Se actualizo el curso.";
    }

    @Override
    public String eliminar(Long idCurso) throws Exception {
        if(idCurso == null){
            throw new Exception("Se debe de ingresar el id del curso.");
        }
        if (!cursoDAO.existsById(idCurso)){
            throw new Exception("El curso con id: " + idCurso + " no existe.");
        }
        //Todo: Faltan las validaciones de reto, mision y cursoEstudiante
        cursoDAO.deleteById(idCurso);
        return "Se elimino exitosamente el curso.";
    }


    @Override
    public List<Curso> findByCorreoEstudiante(String correoEstudiante) throws Exception {
        Validaciones.formatoCorreoValido(correoEstudiante);
        return cursoDAO.findByCorreoEstudiante(correoEstudiante);

    }

    @Override
    public List<Curso> findAll() throws Exception {
        return cursoDAO.findAll();
    }

    @Override
    public Curso findById(Long idCurso) throws Exception {
        if(idCurso == null){
            throw new Exception("Debe ingresar el id de un curso.");
        }
        if(!cursoDAO.existsById(idCurso)){
            throw new Exception("El curso con id: " + idCurso + " no existe.");
        }
        return cursoDAO.findById(idCurso).get();
    }

    public void validaciones(Curso curso) throws Exception{
        if(!estadoDAO.existsById(curso.getEstado().getIdEstado())){
            throw new Exception("El estado que ingreso no es válido.");
        }
        if(!profesorDAO.existsById(curso.getProfesor().getIdProfesor())){
            throw new Exception("El profesor que ingreso no es válido.");
        }
    }
}
