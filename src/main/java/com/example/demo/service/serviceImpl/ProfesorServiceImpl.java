package com.example.demo.service.serviceImpl;

import com.example.demo.dao.ComentarioDAO;
import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.GeneroDAO;
import com.example.demo.dao.ProfesorDAO;
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
    private ComentarioDAO comentarioDAO;

    @Autowired
    private CursoDAO cursoDAO;

    @Override
    public String registarProfesor(Profesor profesor) throws Exception {
        validacionesCrear(profesor);
        profesorDAO.save(profesor);
        return "Se creo exitosamente el profesor";
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
        return "Se actualizo el profesor";
    }

    @Override
    public void eliminar(Long idProfesor) throws Exception {
        if(idProfesor == null){
            throw new Exception("Debe ingresar el id del profesor que desea eliminar");
        }
        if(!profesorDAO.existsById(idProfesor)){
            throw new Exception("No se encontro un profesor con ese Id");
        }
        if(!comentarioDAO.findByIdProfesor(idProfesor).isEmpty()){
            throw new Exception("No se puede eliminar el profesor porque esta asociado a un comentario");
        }
        if(!cursoDAO.findByIdProfesor(idProfesor).isEmpty()){
            throw new Exception("No se puede eliminar el profesor porque esta asociado a un curso");
        }
        profesorDAO.deleteById(idProfesor);
    }

    @Override
    public List<Profesor> listar() throws Exception {
        return profesorDAO.findAll();
    }

    private void validacionesCrear(Profesor profesor) throws Exception {
        if(profesor.getGenero().getIdGenero()==null){
            throw new Exception("Debe ingresar el id un genero");
        }
        if(profesor.getGenero().getIdGenero()<0){
            throw new Exception("Debe ingresar un id genero mayor a 0");
        }
        if(generoDAO.findById(profesor.getGenero().getIdGenero()).toString().equals("Optional.empty")){
            throw new Exception("No existe un genero con ese Id, ingrese un Id valido");
        }
        if(profesor.getNombre()==null || profesor.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del profesor");
        }
        if(Validaciones.isStringLenght(profesor.getNombre(),50)){
            throw new Exception("El nombre del profesor no debe contener más de 50 caracteres");
        }
        if(profesor.getApellido()==null || profesor.getApellido().equals("")){
            throw new Exception("Debe ingresar el apellido del profesor");
        }
        if(Validaciones.isStringLenght(profesor.getApellido(),50)){
            throw new Exception("El apellido no debe contener más de 50 caracteres");
        }
        if(profesor.getCorreo()==null || profesor.getCorreo().equals("")){
            throw new Exception("Debe ingresar el correo del profesor");
        }
        if(Validaciones.isStringLenght(profesor.getCorreo(),50)){
            throw new Exception("El correo no debe contener más de 50 caracteres");
        }
        if(!Validaciones.formatoCorreoValido(profesor.getCorreo())){
            throw new Exception("Debe ingresar un correo valido");
        }
        if(Validaciones.isStringLenght(profesor.getFoto(),80)){
            throw new Exception("El nombre de la foto es muy largo, se acpetan maximo 80 caracteres");
        }
        if(!Validaciones.validExtensionImg(profesor.getFoto())){
            throw new Exception("El formato de la imagen no es valido");
        }
        if(profesor.getUsuarioCreador()==null|| profesor.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario creador");
        }
        if(Validaciones.isStringLenght(profesor.getUsuarioCreador(),50)){
            throw new Exception("El usuario creador no debe contener más de 50 caracteres");
        }
        if(profesor.getFechaCreacion()==null || profesor.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar la fecha de creación");
        }
        Date fechaActual = new Date();
        if(profesor.getFechaCreacion().compareTo(fechaActual)>0){
            throw  new Exception("No se puede ingresar una fecha que aun no ha sucedido");
        }

    }
    private void validacionesActualizar(ProfesorDTO profesorDTO) throws Exception{
        if(profesorDTO.getIdProfesor()==null || profesorDTO.getIdProfesor().toString().equals("")){
            throw new Exception("Debe ingresar el id del profesor que desea editar");
        }
        if(!generoDAO.existsById(profesorDTO.getIdGenero())){
            throw new Exception("No existe un genero con ese id");
        }
        if(profesorDTO.getIdGenero()==null){
            throw new Exception("Debe ingresar el id un genero");
        }
        if(profesorDTO.getIdGenero()<0){
            throw new Exception("Debe ingresar un id genero mayor a 0");
        }
        if(profesorDTO.getNombre()== null || profesorDTO.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del profesor");
        }
        if(Validaciones.isStringLenght(profesorDTO.getNombre(),50)){
            throw new Exception("El nombre del profesor no debe contener más de 50 caracteres");
        }
        if(profesorDTO.getApellido()==null || profesorDTO.getApellido().equals("")){
            throw new Exception("Debe ingresar el apellido del profesor");
        }
        if(Validaciones.isStringLenght(profesorDTO.getApellido(),50)){
            throw new Exception("El apellido no debe contener más de 50 caracteres");
        }
        if(profesorDTO.getCorreo()==null || profesorDTO.getCorreo().equals("")){
            throw new Exception("Debe ingresar el correo del profesor");
        }
        if(Validaciones.isStringLenght(profesorDTO.getCorreo(),50)){
            throw new Exception("El correo no debe contener más de 50 caracteres");
        }
        if(!Validaciones.formatoCorreoValido(profesorDTO.getCorreo())){
            throw new Exception("Debe ingresar un correo valido");
        }
        if(Validaciones.isStringLenght(profesorDTO.getFoto(),80)){
            throw new Exception("El nombre de la foto es muy largo, se acpetan maximo 80 caracteres");
        }
        if(!Validaciones.validExtensionImg(profesorDTO.getFoto())){
            throw new Exception("El formato de la imagen no es valido");
        }
        if(profesorDTO.getUsuarioModificador()==null|| profesorDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario modificador");
        }
        if(Validaciones.isStringLenght(profesorDTO.getUsuarioModificador(),50)){
            throw new Exception("El usuario modificador no debe contener más de 50 caracteres");
        }
        if(profesorDTO.getFechaModificacion()==null || profesorDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar la fecha de modificación");
        }
        Date fechaActual = new Date();
        if(profesorDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw  new Exception("No se puede ingresar una fecha que aun no ha sucedido");
        }
    }
}
