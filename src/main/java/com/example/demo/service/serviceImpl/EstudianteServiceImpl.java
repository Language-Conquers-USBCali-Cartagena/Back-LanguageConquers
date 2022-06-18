package com.example.demo.service.serviceImpl;

import com.example.demo.dao.*;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

@Scope("singleton")
@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    private EstudianteDAO estudianteDAO;

    @Autowired
    private SemestreDAO semestreDAO;

    @Autowired
    private GeneroDAO generoDAO;

    @Autowired
    private AvatarDAO avatarDAO;

    @Autowired
    private EstadoDAO estadoDAO;

    @Autowired
    private ProgramaDAO programaDAO;


    @Override
    public String crearEstudiante(EstudianteDTO estudianteDTO) throws Exception {
        Estudiante estudiante = new Estudiante();
        if(estudianteDTO.getIdAvatar() == null){
            throw new Exception("Debe ingresar un id avatar");
        }
        if(estudianteDTO.getIdGenero() == null){
            throw new Exception("Debe ingresar un id genero");
        }
        if(estudianteDTO.getIdSemestre() == null){
            throw new Exception("Debe ingresar un id semestre");
        }
        if(estudianteDTO.getIdPrograma() == null){
            throw new Exception("Debe ingresar un id programa");
        }
        if(estudianteDTO.getIdEstado() == null){
            throw new Exception("Debe ingresar un id estado");
        }
        if(estudianteDTO.getIdAvatar()<0){
            throw new Exception("Debe ingresar un id avatar mayor a 0");
        }
        if(estudianteDTO.getIdPrograma()<0){
            throw new Exception("Debe ingresar un id programa mayor a 0");
        }
        if(estudianteDTO.getIdEstado()<0){
            throw new Exception("Debe ingresar un id estado mayor a 0");
        }
        if(estudianteDTO.getIdSemestre()<0){
            throw new Exception("Debe ingresar un id semestre mayor a 0");
        }
        if(estudianteDTO.getIdGenero()<0){
            throw new Exception("Debe ingresar un id genero mayor a 0");
        }
        if(avatarDAO.findById(estudianteDTO.getIdAvatar()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id avatar que exista");
        }
        if(generoDAO.findById(estudianteDTO.getIdGenero()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id genero que exista");
        }
        if(semestreDAO.findById(estudianteDTO.getIdSemestre()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id semestre que exista");
        }
        if(estadoDAO.findById(estudianteDTO.getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id estado que exista");
        }
        if(programaDAO.findById(estudianteDTO.getIdPrograma()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id programa que exista");
        }
        if(estudianteDTO.getNombre() == null || estudianteDTO.getNombre().trim().equals("")){
            throw new Exception("Debe ingresar el nombre del estudiante");
        }
        if(estudianteDTO.getNombre().length()>80){
            throw new Exception("El nombre del estudiante es muy largo");
        }
        if(estudianteDTO.getApellido() == null || estudianteDTO.getApellido().trim().equals("")){
            throw new Exception("Debe ingresar el apellido del estudiante");
        }
        if(estudianteDTO.getApellido().length()>80){
            throw new Exception("El apellido del estudiante es muy largo");
        }
        if(estudianteDTO.getNickName() == null || estudianteDTO.getNickName().trim().equals("")){
            throw new Exception("Debe ingresar un nick name para el estudiante");
        }
        if(estudianteDTO.getNickName().length()>80){
            throw new Exception("Debe ingresar un nick name para el estudiante");
        }
        if(estudianteDTO.getPuntaje()<0){
            throw new Exception("El puntaje no debe ser negativo");
        }
        if(estudianteDTO.getCorreo() == null || estudianteDTO.getCorreo().trim().equals("")){
            throw new Exception("Debe ingresar un correo del estudiante");
        }
       /* if(!Validaciones.formatoCorreoValido(estudianteDTO.getCorreoEstudiante())){
            throw new Exception("Debe ingresar un correo valido");
        }*/
        if(estudianteDTO.getFechaNacimiento() == null){
            throw new Exception("Debe ingresar una fecha de nacimiento");
        }
        Date fecha = new Date();
        if(estudianteDTO.getFechaNacimiento().compareTo(fecha)>0){
            throw new Exception("Digite una fecha de nacimiento valida");
        }

        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setNickName(estudianteDTO.getNickName());
        estudiante.setPuntaje(estudianteDTO.getPuntaje());
        estudiante.setCorreo(estudianteDTO.getCorreo());
        estudiante.setSemestre(semestreDAO.findById(estudianteDTO.getIdSemestre()).get());
        estudiante.setGenero(generoDAO.findById(estudianteDTO.getIdGenero()).get());
        estudiante.setAvatar(avatarDAO.findById(estudianteDTO.getIdAvatar()).get());
        estudiante.setEstado(estadoDAO.findById(estudianteDTO.getIdEstado()).get());
        estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudiante.setPrograma(programaDAO.findById(estudianteDTO.getIdPrograma()).get());
        estudiante.setUsuarioCreador(estudianteDTO.getUsuarioCreador());
        estudiante.setFechaCreacion(estudianteDTO.getFechaCreacion());
        estudianteDAO.save(estudiante);
        return "Se creo exitosamente al estudiante";
    }
}
