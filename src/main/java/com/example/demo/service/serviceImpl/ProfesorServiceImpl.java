package com.example.demo.service.serviceImpl;

import com.example.demo.dao.*;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.ProfesorDTO;
import com.example.demo.service.ProfesorService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    private ProfesorDAO profesorDAO;
    @Autowired
    private GeneroDAO generoDAO;

    @Autowired
    private CursoDAO cursoDAO;

    @Autowired
    private EstudianteDAO estudianteDAO;
    @Override
    public String registarProfesor(Profesor profesor) throws Exception {
        validacionesCrear(profesor);
        profesorDAO.save(profesor);
        return "Se creo exitosamente el profesor.";
    }

    @Override
    public String actualizar(ProfesorDTO profesorDTO) throws Exception {
        validacionesActualizar(profesorDTO);
        Profesor profesor = null;
        profesor = profesorDAO.findById(profesorDTO.getIdProfesor()).orElse(null);
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setApellido(profesorDTO.getApellido());
        profesor.setCorreo(profesorDTO.getCorreo());
        profesor.setFoto(profesorDTO.getFoto());
        profesor.setUsuarioModificador(profesorDTO.getUsuarioModificador());
        profesor.setFechaModificacion(profesorDTO.getFechaModificacion());
        profesorDAO.save(profesor);
        return "Se actualizo el profesor.";
    }

    @Override
    public String eliminar(Long idProfesor) throws Exception {
        if(idProfesor == null){
            throw new Exception("Debe ingresar el id del profesor que desea eliminar.");
        }
        if(!profesorDAO.existsById(idProfesor)){
            throw new Exception("No se encontró un profesor con ese Id.");
        }
        if(!cursoDAO.findByIdProfesor(idProfesor).isEmpty()){
            throw new Exception("No se puede eliminar el profesor porque esta asociado a un curso.");
        }
        profesorDAO.deleteById(idProfesor);
        return "Se elimino exitosamente el profesor.";
    }

    @Override
    public List<Profesor> listar() throws Exception {
        return profesorDAO.findAll();
    }

    @Override
    public Boolean existePorCorreo(String correo) throws Exception {
        if(!Validaciones.formatoCorreoValido(correo)){
            throw new Exception("El formato del correo no es válido.");
        }
        return profesorDAO.existsByCorreo(correo);
    }

    @Override
    public Profesor findByCorreo(String correo) throws Exception {
        if(!Validaciones.formatoCorreoValido(correo)){
            throw new Exception("El formato del correo no es válido.");
        }
        return profesorDAO.findByCorreo(correo);
    }

    @Override
    public Profesor findById(Long idProfesor) throws Exception {
        if(idProfesor == null){
            throw new Exception("Debe ingresar el id de un profesor.");
        }
        if(!profesorDAO.existsById(idProfesor)){
            throw new Exception("El profesor con id: " + idProfesor + " no existe.");
        }
        return profesorDAO.findById(idProfesor).get();
    }

    @Override
    public int cantidadProfesores() throws Exception {
        int cantidad = profesorDAO.totalProfesores();
        return cantidad;
    }

    private void validacionesCrear(Profesor profesor) throws Exception {
        if(profesor.getGenero().getIdGenero()==null){
            throw new Exception("Debe ingresar el id un genero.");
        }
        if(profesor.getGenero().getIdGenero()<0){
            throw new Exception("Debe ingresar un id genero válido.");
        }
        if(generoDAO.findById(profesor.getGenero().getIdGenero()).toString().equals("Optional.empty")){
            throw new Exception("No existe un genero con ese id, ingrese un id válido.");
        }
        if(profesor.getNombre()==null || profesor.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del profesor.");
        }
        if(Validaciones.isStringLenght(profesor.getNombre(),50)){
            throw new Exception("El nombre del profesor no debe contener más de 50 caracteres.");
        }
        if(profesor.getApellido()==null || profesor.getApellido().equals("")){
            throw new Exception("Debe ingresar el apellido del profesor.");
        }
        if(Validaciones.isStringLenght(profesor.getApellido(),50)){
            throw new Exception("El apellido no debe contener más de 50 caracteres.");
        }
        if(profesor.getCorreo()==null || profesor.getCorreo().equals("")){
            throw new Exception("Debe ingresar el correo del profesor");
        }
        if(Validaciones.isStringLenght(profesor.getCorreo(),50)){
            throw new Exception("El correo no debe contener más de 50 caracteres.");
        }
        if(!Validaciones.formatoCorreoValido(profesor.getCorreo())){
            throw new Exception("Debe ingresar un correo válido.");
        }
        if(Validaciones.isStringLenght(profesor.getFoto(),250)){
            throw new Exception("El nombre de la foto es muy largo, se aceptan máximo 80 caracteres.");
        }
        if(profesor.getUsuarioCreador()==null|| profesor.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario creador.");
        }
        if(Validaciones.isStringLenght(profesor.getUsuarioCreador(),50)){
            throw new Exception("El usuario creador no debe contener más de 50 caracteres.");
        }
        if(profesor.getFechaCreacion()==null || profesor.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar la fecha de creación.");
        }
        Date fechaActual = new Date();
        if(profesor.getFechaCreacion().compareTo(fechaActual)>0){
            throw  new Exception("No se puede ingresar una fecha que aun no ha sucedido.");
        }
        if(estudianteDAO.existsByCorreo(profesor.getCorreo()) || profesorDAO.existsByCorreo(profesor.getCorreo())){
            throw new Exception("El correo ya existe.");
        }

    }
    private void validacionesActualizar(ProfesorDTO profesorDTO) throws Exception{
        if(profesorDTO.getIdProfesor()==null || profesorDTO.getIdProfesor().toString().equals("")){
            throw new Exception("Debe ingresar el id del profesor que desea editar.");
        }
        if(!generoDAO.existsById(profesorDTO.getIdGenero())){
            throw new Exception("No existe un genero con ese id.");
        }
        if(profesorDTO.getIdGenero()==null){
            throw new Exception("Debe ingresar el id un genero.");
        }
        if(profesorDTO.getIdGenero()<0){
            throw new Exception("Debe ingresar un id genero válido.");
        }
        if(profesorDTO.getNombre()== null || profesorDTO.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del profesor.");
        }
        if(Validaciones.isStringLenght(profesorDTO.getNombre(),50)){
            throw new Exception("El nombre del profesor no debe contener más de 50 caracteres.");
        }
        if(profesorDTO.getApellido()==null || profesorDTO.getApellido().equals("")){
            throw new Exception("Debe ingresar el apellido del profesor.");
        }
        if(Validaciones.isStringLenght(profesorDTO.getApellido(),50)){
            throw new Exception("El apellido no debe contener más de 50 caracteres.");
        }
        if(profesorDTO.getCorreo()==null || profesorDTO.getCorreo().equals("")){
            throw new Exception("Debe ingresar el correo del profesor.");
        }
        if(Validaciones.isStringLenght(profesorDTO.getCorreo(),50)){
            throw new Exception("El correo no debe contener más de 50 caracteres.");
        }
        if(!Validaciones.formatoCorreoValido(profesorDTO.getCorreo())){
            throw new Exception("Debe ingresar un correo válido.");
        }
        if(Validaciones.isStringLenght(profesorDTO.getFoto(),250)){
            throw new Exception("El nombre de la foto es muy largo, se aceptan máximo 80 caracteres.");
        }
        if(profesorDTO.getUsuarioModificador()==null|| profesorDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario modificador.");
        }
        if(Validaciones.isStringLenght(profesorDTO.getUsuarioModificador(),50)){
            throw new Exception("El usuario modificador no debe contener más de 50 caracteres.");
        }
        if(profesorDTO.getFechaModificacion()==null || profesorDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar la fecha de modificación.");
        }
        Date fechaActual = new Date();
        if(profesorDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw  new Exception("No se puede ingresar una fecha que aun no ha sucedido.");
        }

    }
}
