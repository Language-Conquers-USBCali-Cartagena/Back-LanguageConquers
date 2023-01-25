package com.example.demo.service.serviceImpl;

import com.example.demo.dao.*;
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

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    CursoDAO cursoDAO;

    @Autowired
    EstadoDAO estadoDAO;

    @Autowired
    ProfesorDAO profesorDAO;

    @Autowired
    RetoDAO retoDAO;

    @Autowired
    MisionDAO misionDAO;

    @Autowired
    CursoEstudianteDAO cursoEstudianteDAO;


    @Override
    public String registrar(Curso curso) throws Exception {
        try {
            validacionesCrear(curso);
            cursoDAO.save(curso);
            return "Se creo exitosamente el curso.";
        }catch (Exception e){
            return e.getMessage().toString();
        }

    }

    @Override
    public String actualizar(CursoDTO cursoDTO) throws Exception {
        Curso curso = null;
        validacionesActualizar(cursoDTO);
        curso = cursoDAO.findById(cursoDTO.getIdCurso()).orElse(null);
        curso.setNombre(cursoDTO.getNombre());
        curso.setPassword(cursoDTO.getPassword());
        curso.setCantidadEstudiantes(cursoDTO.getCantidadEstudiantes());
        curso.setInicioCurso(cursoDTO.getInicioCurso());
        curso.setFinCurso(cursoDTO.getFinCurso());
        curso.setProgreso(cursoDTO.getProgreso());
        curso.setProfesor(profesorDAO.findById(cursoDTO.getIdProfesor()).orElse(null));
        curso.setEstado(estadoDAO.findById(cursoDTO.getIdEstado()).orElse(null));
        curso.setFechaModificacion(cursoDTO.getFechaModificacion());
        curso.setUsuarioModificador(cursoDTO.getUsuarioModificador());
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
        if(!retoDAO.findByIdCurso(idCurso).isEmpty()){
            throw new Exception("No se puede eliminar el curso porque esta siendo utilizado en un reto.");
        }
        if(!misionDAO.findByIdCurso(idCurso).isEmpty()){
            throw new Exception("No se puede eliminar el curso porque esta siendo utilizado en una misión.");
        }
        if(!cursoEstudianteDAO.findByIdCurso(idCurso).isEmpty()){
            throw new Exception("No se puede eliminar el curso porque esta siendo utilizado en un curso estudiante.");
        }
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

    public void validacionesCrear(Curso curso) throws Exception{
        if(curso.getNombre() == null || curso.getNombre().trim().equals("")){
            throw new Exception("Debe ingresar el nombre del curso.");
        }
        if(Validaciones.isStringLenght(curso.getNombre(),50)){
            throw new Exception("El nombre del curso es muy largo, solo se aceptan 50 caracteres.");
        }
        if(curso.getPassword() == null || curso.getPassword().trim().equals("")){
            throw new Exception("Se debe ingresar una contraseña para el curso.");
        }
        if(Validaciones.isStringLenght(curso.getPassword(), 50)){
            throw new Exception("La contraseña es muy larga, solo se aceptan 50 caracteres.");
        }
        if(curso.getCantidadEstudiantes()<1){
            throw new Exception("Se debe ingresar la cantidad de estudiantes que tendrán acceso al curso.");
        }
        if(curso.getProgreso()!=0){
            throw new Exception("Al crear el curso se crea con el progreso en el 0%.");
        }
        if(curso.getInicioCurso() == null){
            throw new Exception("Debe ingresar una fecha de inicio del curso.");
        }
        Date fechaActual = new Date();
        if(curso.getInicioCurso().compareTo(fechaActual)>0){
            throw new Exception("Digite una fecha de inicio del curso válida.");
        }
        if(curso.getFinCurso().compareTo(curso.getInicioCurso())>0){
            throw new Exception("La fecha de finalización del curso no puede ser antes de la fecha de inicio.");
        }
        if(curso.getEstado().getIdEstado() == null){
            throw new Exception("Debe ingresar un id estado.");
        }
        if(curso.getProfesor().getIdProfesor() == null){
            throw new Exception("Debe ingresar un id del profesor.");
        }
        if(curso.getEstado().getIdEstado()<0 || curso.getEstado().getIdEstado().toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id estado válido.");
        }
        if(curso.getProfesor().getIdProfesor()< 0 || curso.getProfesor().getIdProfesor().toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id del profesor válido.");
        }
        if(!estadoDAO.existsById(curso.getEstado().getIdEstado())){
            throw new Exception("El estado que ingreso no es válido.");
        }
        if(!profesorDAO.existsById(curso.getProfesor().getIdProfesor())){
            throw new Exception("El profesor que ingreso no es válido.");
        }
        if(curso.getUsuarioCreador()==null || curso.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(curso.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(curso.getFechaCreacion()==null || curso.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
    }

    private void validacionesActualizar(CursoDTO cursoDTO)throws Exception{
        if(cursoDTO.getIdCurso() == null || cursoDTO.getIdCurso().toString().equals("")){
            throw new Exception("Se debe ingresar el id del curso que desea actualizar.");
        }
        if(!cursoDAO.existsById(cursoDTO.getIdCurso())){
            throw new Exception("No se encontró el curso con ese id.");
        }
        if(!estadoDAO.existsById(cursoDTO.getIdEstado())){
            throw new Exception("No se encontró el estado con ese id.");
        }
        if(!profesorDAO.existsById(cursoDTO.getIdProfesor())){
            throw new Exception("No se encontró el profesor con ese id.");
        }
        if(cursoDTO.getNombre() == null || cursoDTO.getNombre().trim().equals("")){
            throw new Exception("Debe ingresar el nombre del curso.");
        }
        if(Validaciones.isStringLenght(cursoDTO.getNombre(),50)){
            throw new Exception("El nombre del curso es muy largo, solo se aceptan 50 caracteres.");
        }
        if(cursoDTO.getPassword() == null || cursoDTO.getPassword().trim().equals("")){
            throw new Exception("Se debe ingresar una contraseña para el curso.");
        }
        if(Validaciones.isStringLenght(cursoDTO.getPassword(), 50)){
            throw new Exception("La contraseña es muy larga, solo se aceptan 50 caracteres.");
        }
        if(cursoDTO.getCantidadEstudiantes()<1){
            throw new Exception("Se debe ingresar la cantidad de estudiantes que tendrán acceso al curso.");
        }
        if(cursoDTO.getProgreso()!=0){
            throw new Exception("Al crear el curso se crea con el progreso en el 0%.");
        }
        if(cursoDTO.getInicioCurso() == null){
            throw new Exception("Debe ingresar una fecha de inicio del curso.");
        }
        Date fechaActual = new Date();
        if(cursoDTO.getInicioCurso().compareTo(fechaActual)>0){
            throw new Exception("Digite una fecha de inicio del curso válida.");
        }
        if(cursoDTO.getFinCurso().compareTo(fechaActual)>0){
            throw new Exception("Digite una fecha de finalización del curso válida.");
        }
        if(cursoDTO.getFinCurso().compareTo(cursoDTO.getInicioCurso())>0){
            throw new Exception("La fecha de finalización del curso no puede ser antes de la fecha de inicio.");
        }
        if(cursoDTO.getIdEstado() == null){
            throw new Exception("Debe ingresar un id estado.");
        }
        if(cursoDTO.getIdProfesor() == null){
            throw new Exception("Debe ingresar un id del profesor.");
        }
        if(cursoDTO.getIdEstado()<0 || cursoDTO.getIdEstado().toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id estado válido.");
        }
        if(cursoDTO.getIdProfesor()< 0 || cursoDTO.getIdProfesor().toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id del profesor válido.");
        }
        if(cursoDTO.getUsuarioModificador()==null || cursoDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(cursoDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.");
        }
        if(cursoDTO.getFechaModificacion()==null || cursoDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        if(cursoDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }

    }
}
