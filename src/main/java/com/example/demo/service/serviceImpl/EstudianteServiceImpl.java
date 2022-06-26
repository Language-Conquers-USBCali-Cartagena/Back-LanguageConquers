package com.example.demo.service.serviceImpl;


import com.example.demo.dao.*;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private RetoEstudianteDAO retoEstudianteDAO;
    @Autowired
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;
    @Autowired
    private CursoEstudianteDAO cursoEstudianteDAO;
    @Autowired
    private MisionEstudianteDAO misionEstudianteDAO;
    @Autowired
    private ComentarioDAO comentarioDAO;
    @Override
    public String crearEstudiante(Estudiante estudiante) throws Exception {
        validacionesCrear(estudiante);
        estudianteDAO.save(estudiante);
        return "Se creo exitosamente al estudiante";
    }

    @Override
    public String actualizar(EstudianteDTO estudianteDTO) throws Exception {
        Estudiante estudiante = null;
        validacionesActualizar(estudianteDTO);
        estudiante = estudianteDAO.findById(estudianteDTO.getIdEstudiante()).orElse(null);
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setNickName(estudianteDTO.getNickName());
        estudiante.setPuntaje(estudianteDTO.getPuntaje());
        estudiante.setCorreo(estudianteDTO.getCorreo());
        estudiante.setSemestre(semestreDAO.findById(estudianteDTO.getIdSemestre()).orElse(null));
        estudiante.setGenero(generoDAO.findById(estudianteDTO.getIdGenero()).orElse(null));
        estudiante.setAvatar(avatarDAO.findById(estudianteDTO.getIdAvatar()).orElse(null));
        estudiante.setEstado(estadoDAO.findById(estudianteDTO.getIdEstado()).orElse(null));
        estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudiante.setPrograma(programaDAO.findById(estudianteDTO.getIdPrograma()).orElse(null));
        estudiante.setUsuarioModificador(estudianteDTO.getUsuarioModificador());
        estudiante.setFechaModificacion(estudianteDTO.getFechaModificacion());
        estudianteDAO.save(estudiante);
        return "Se actualizo exitosamente al estudiante";
    }

    @Override
    public void eliminar(Long idEstudiante) throws Exception {
        if(idEstudiante == null){
            throw new Exception("El id del estudiante es obligatorio");
        }
        if(!estudianteDAO.existsById(idEstudiante)){
            throw new Exception("No se encontro un estudiante con ese Id");
        }
        if(!retoEstudianteDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un reto estudiante");
        }
        if(!articulosAdquiridosDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un articulo adquirido");
        }
        if(!cursoEstudianteDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un curso estudiante");
        }
        if(!misionEstudianteDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un mision estudiante");
        }
        if(!comentarioDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un comentario");
        }
        estudianteDAO.deleteById(idEstudiante);
    }

    @Override
    public List<Estudiante> listar() throws Exception {
        return estudianteDAO.findAll();
    }

    private void validacionesCrear(Estudiante estudiante) throws Exception {
        if(estudiante.getAvatar().getIdAvatar() == null){
            throw new Exception("Debe ingresar un id avatar");
        }
        if(estudiante.getGenero().getIdGenero() == null){
            throw new Exception("Debe ingresar un id genero");
        }
        if(estudiante.getSemestre().getIdSemestre() == null){
            throw new Exception("Debe ingresar un id semestre");
        }
        if(estudiante.getPrograma().getIdPrograma() == null){
            throw new Exception("Debe ingresar un id programa");
        }
        if(estudiante.getEstado().getIdEstado() == null){
            throw new Exception("Debe ingresar un id estado");
        }
        if(estudiante.getAvatar().getIdAvatar()<0){
            throw new Exception("Debe ingresar un id avatar mayor a 0");
        }
        if(estudiante.getPrograma().getIdPrograma()<0){
            throw new Exception("Debe ingresar un id programa mayor a 0");
        }
        if(estudiante.getEstado().getIdEstado()<0){
            throw new Exception("Debe ingresar un id estado mayor a 0");
        }
        if(estudiante.getSemestre().getIdSemestre()<0){
            throw new Exception("Debe ingresar un id semestre mayor a 0");
        }
        if(estudiante.getGenero().getIdGenero()<0){
            throw new Exception("Debe ingresar un id genero mayor a 0");
        }
        if(avatarDAO.findById(estudiante.getAvatar().getIdAvatar()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id avatar que exista");
        }
        if(generoDAO.findById(estudiante.getGenero().getIdGenero()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id genero que exista");
        }
        if(semestreDAO.findById(estudiante.getSemestre().getIdSemestre()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id semestre que exista");
        }
        if(estadoDAO.findById(estudiante.getEstado().getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id estado que exista");
        }
        if(programaDAO.findById(estudiante.getPrograma().getIdPrograma()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id programa que exista");
        }
        if(estudiante.getNombre() == null || estudiante.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del estudiante");
        }
        if(Validaciones.isStringLenght(estudiante.getNombre(),80)){
            throw new Exception("El nombre del estudiante es muy largo");
        }
        if(estudiante.getApellido() == null || estudiante.getApellido().equals("")){
            throw new Exception("Debe ingresar el apellido del estudiante");
        }
        if(Validaciones.isStringLenght(estudiante.getApellido(),80)){
            throw new Exception("El apellido del estudiante es muy largo");
        }
        if(estudiante.getNickName() == null || estudiante.getNickName().equals("")){
            throw new Exception("Debe ingresar un nick name para el estudiante");
        }
        if(Validaciones.isStringLenght(estudiante.getNickName(),80)){
            throw new Exception("Debe ingresar un nick name para el estudiante, no mayor a 80 caracteres");
        }
        if(estudiante.getPuntaje()<0){
            throw new Exception("El puntaje no debe ser negativo");
        }
        if(estudiante.getCorreo() == null || estudiante.getCorreo().equals("")){
            throw new Exception("Debe ingresar un correo del estudiante");
        }
        if(!Validaciones.formatoCorreoValido(estudiante.getCorreo())) {
            throw new Exception("Debe ingresar un correo valido");
        }
        if(estudiante.getFechaNacimiento() == null){
            throw new Exception("Debe ingresar una fecha de nacimiento");
        }
        Date fechaActual = new Date();
        if(estudiante.getFechaNacimiento().compareTo(fechaActual)>0){
            throw new Exception("Digite una fecha de nacimiento valida");
        }
        if(estudiante.getUsuarioCreador()==null || estudiante.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador");
        }
        if(Validaciones.isStringLenght(estudiante.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres");
        }
        if(estudiante.getFechaCreacion()==null || estudiante.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación");
        }
        if(estudiante.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
    }
    private void validacionesActualizar(EstudianteDTO estudianteDTO) throws Exception {
        if(estudianteDTO.getIdEstudiante() == null){
            throw new Exception("Debe ingresar el id del estudiante que desea actualizar");
        }
        if(!estudianteDAO.existsById(estudianteDTO.getIdEstudiante())){
            throw new Exception("No se encontro el estudiante con ese Id");
        }
        if(!avatarDAO.existsById(estudianteDTO.getIdAvatar())){
            throw new Exception("No existe un avatar con ese id");
        }
        if(!generoDAO.existsById(estudianteDTO.getIdGenero())){
            throw new Exception("No existe un genero con ese id");
        }
        if(!estadoDAO.existsById(estudianteDTO.getIdEstado())){
            throw new Exception("No existe un estado con ese id");
        }
        if(!programaDAO.existsById(estudianteDTO.getIdPrograma())){
            throw new Exception("No existe un programa con ese id");
        }
        if(!semestreDAO.existsById(estudianteDTO.getIdSemestre())){
            throw new Exception("No existe un semestre con ese id");
        }
        if(estudianteDTO.getIdAvatar().toString().equals("")){
            throw new Exception("Debe ingresar un id avatar");
        }
        if(estudianteDTO.getIdGenero().toString().equals("")){
            throw new Exception("Debe ingresar un id genero");
        }
        if(estudianteDTO.getIdSemestre().toString().equals("")){
            throw new Exception("Debe ingresar un id semestre");
        }
        if(estudianteDTO.getIdPrograma().toString().equals("")){
            throw new Exception("Debe ingresar un id programa");
        }
        if(estudianteDTO.getIdEstado().toString().equals("")){
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
        if(!Validaciones.formatoCorreoValido(estudianteDTO.getCorreo())) {
            throw new Exception("Debe ingresar un correo valido");
        }
        if(estudianteDTO.getFechaNacimiento() == null){
            throw new Exception("Debe ingresar una fecha de nacimiento");
        }
        Date fechaActual = new Date();
        if(estudianteDTO.getFechaNacimiento().compareTo(fechaActual)>0){
            throw new Exception("Digite una fecha de nacimiento valida");
        }
        if(estudianteDTO.getUsuarioModificador()==null || estudianteDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador");
        }
        if(Validaciones.isStringLenght(estudianteDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres");
        }
        if(estudianteDTO.getFechaModificacion()==null || estudianteDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación");
        }
        if(estudianteDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
    }
}
