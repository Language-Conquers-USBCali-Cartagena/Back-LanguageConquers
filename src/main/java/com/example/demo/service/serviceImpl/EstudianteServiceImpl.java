package com.example.demo.service.serviceImpl;


import com.example.demo.dao.*;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
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
    private ProfesorDAO profesorDAO;

    @Autowired
    private RetoEstudianteDAO retoEstudianteDAO;
    @Autowired
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;
    @Autowired
    private CursoEstudianteDAO cursoEstudianteDAO;
    @Autowired
    private MisionEstudianteDAO misionEstudianteDAO;

    @Override
    public String crearEstudiante(Estudiante estudiante) throws Exception {
        validacionesCrear(estudiante);
        estudianteDAO.save(estudiante);
        return "Se creo exitosamente el estudiante.";
    }

    @Override
    public String actualizar(Estudiante estudiante) throws Exception {
        validacionesActualizar(estudiante);
        estudianteDAO.save(estudiante);
        return "Se actualizo exitosamente el estudiante.";
    }

    @Override
    public String eliminar(Long idEstudiante) throws Exception {
        if(idEstudiante == null){
            throw new Exception("El id del estudiante es obligatorio.");
        }
        if(!estudianteDAO.existsById(idEstudiante)){
            throw new Exception("No se encontró un estudiante con ese id.");
        }
        if(!retoEstudianteDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un reto estudiante.");
        }
        if(!articulosAdquiridosDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un artículo adquirido.");
        }
        if(!cursoEstudianteDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un curso estudiante.");
        }
        if(!misionEstudianteDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se puede eliminar el estudiante porque se encuentra asociado a un misión estudiante.");
        }

        estudianteDAO.deleteById(idEstudiante);
        return "Se elimino exitosamente el estudiante.";
    }

    @Override
    public List<Estudiante> listar() throws Exception {
        return estudianteDAO.findAll();
    }
    @Override
    public Boolean existePorCorreo(String correo) throws Exception {

        if(!Validaciones.formatoCorreoValido(correo)){
            throw new Exception("El formato del correo no es valido");
        }
        return estudianteDAO.existsByCorreo(correo);

    }

    @Override
    public Estudiante findByCorreo(String correo) throws Exception {


        if(correo.equals("")){
            throw new Exception("No existe estudiante registrado con este correo");
        }
        if(!Validaciones.formatoCorreoValido(correo)){
            throw new Exception("El formato del correo no es válido.");
        }
        Estudiante estudiante = estudianteDAO.findByCorreo(correo);
        return estudiante;
    }

    @Override
    public Estudiante findById(Long idEstudiante) throws Exception {
        if(idEstudiante ==null){
            throw new Exception("Debe ingresar el id de un estudiante.");
        }
        if(!estudianteDAO.existsById(idEstudiante)){
            throw new Exception("El estudiante con  ese id no existe.");
        }
        return estudianteDAO.findById(idEstudiante).get();
    }

    @Override
    public List<Estudiante> rankingEstudiante() throws Exception {
        List<Estudiante> estudiantes = estudianteDAO.rankingEstudiantes();
        if(estudiantes.isEmpty()){
            throw new Exception("No hay estudiantes.");
        }
        return estudiantes;
    }

    @Override
    public int cantidadEstudiantes() throws Exception {
        int cantidad = estudianteDAO.totalEstudiante();
        return cantidad;
    }

    @Override
    public int promedioMonedasGanadasEstudiantes() throws Exception {
        int monedasGanadas = estudianteDAO.promedioMonedasGanadasEstudiantes();
        return monedasGanadas;
    }

    private void validacionesCrear(Estudiante estudiante) throws Exception {
        if(estudiante.getAvatar().getIdAvatar() == null){
            throw new Exception("Debe ingresar un id avatar.");
        }
        if(estudiante.getGenero().getIdGenero() == null){
            throw new Exception("Debe ingresar un id genero.");
        }
        if(estudiante.getSemestre().getIdSemestre() == null){
            throw new Exception("Debe ingresar un id semestre.");
        }
        if(estudiante.getPrograma().getIdPrograma() == null){
            throw new Exception("Debe ingresar un id programa.");
        }
        if(estudiante.getEstado().getIdEstado() == null){
            throw new Exception("Debe ingresar un id estado.");
        }
        if(estudiante.getAvatar().getIdAvatar()<0){
            throw new Exception("Debe ingresar un id avatar válido.");
        }
        if(estudiante.getPrograma().getIdPrograma()<0){
            throw new Exception("Debe ingresar un id programa válido.");
        }
        if(estudiante.getEstado().getIdEstado()<0){
            throw new Exception("Debe ingresar un id estado válido.");
        }
        if(estudiante.getSemestre().getIdSemestre()<0){
            throw new Exception("Debe ingresar un id semestre válido.");
        }
        if(estudiante.getGenero().getIdGenero()<0){
            throw new Exception("Debe ingresar un id genero válido.");
        }
        if(!avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())){
            throw new Exception("Debe ingresar un id avatar válido.");
        }
        if(!generoDAO.existsById(estudiante.getGenero().getIdGenero())){
            throw new Exception("Debe ingresar un id genero válido.");
        }
        if(!semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())){
            throw new Exception("Debe ingresar un id semestre válido.");
        }
        if(!estadoDAO.existsById(estudiante.getEstado().getIdEstado())){
            throw new Exception("Debe ingresar un id estado válido.");
        }
        if(!programaDAO.existsById(estudiante.getPrograma().getIdPrograma())){
            throw new Exception("Debe ingresar un id programa válido.");
        }
        if(estudiante.getNombre() == null || estudiante.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del estudiante.");
        }
        if(Validaciones.isStringLenght(estudiante.getNombre(),80)){
            throw new Exception("El nombre del estudiante es muy largo.");
        }
        if(estudiante.getApellido() == null || estudiante.getApellido().equals("")){
            throw new Exception("Debe ingresar el apellido del estudiante.");
        }
        if(Validaciones.isStringLenght(estudiante.getApellido(),80)){
            throw new Exception("El apellido del estudiante es muy largo.");
        }
        if(estudiante.getNickName() == null || estudiante.getNickName().equals("")){
            throw new Exception("Debe ingresar un nickname para el estudiante.");
        }
        if(Validaciones.isStringLenght(estudiante.getNickName(),80)){
            throw new Exception("Debe ingresar un nickname para el estudiante, no mayor a 80 caracteres.");
        }
        if(estudiante.getPuntaje()<0){
            throw new Exception("El puntaje no debe ser negativo.");
        }
        if(estudiante.getMonedasObtenidas()<0){
                throw new Exception("Las monedas obtenidas no debe ser negativo.");
        }
        if(estudiante.getMonedasObtenidas()!=0){
            throw new Exception("Las monedas obtenidas al momento de crear el estudiante son 0.");
        }
        if(estudiante.getCorreo() == null || estudiante.getCorreo().equals("")){
            throw new Exception("Debe ingresar un correo del estudiante.");
        }
        if(!Validaciones.formatoCorreoValido(estudiante.getCorreo())) {
            throw new Exception("Debe ingresar un correo válido.");
        }
        if(estudiante.getFechaNacimiento() == null){
            throw new Exception("Debe ingresar una fecha de nacimiento.");
        }
        LocalDate fechaNamientoLocalDate = estudiante.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNamientoLocalDate, ahora);
        int edad = periodo.getYears();


        if(estudiante.getUsuarioCreador()==null || estudiante.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(estudiante.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(estudiante.getFechaCreacion()==null){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
//        if(estudiante.getFechaCreacion().compareTo(fechaActual)>=0){
//            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
//        }
        if(profesorDAO.existsByCorreo(estudiante.getCorreo()) || estudianteDAO.existsByCorreo(estudiante.getCorreo())){
            throw new Exception("El correo ya existe.");
        }

    }
    private void validacionesActualizar(Estudiante estudiante) throws Exception {
        if(estudiante.getIdEstudiante() == null){
            throw new Exception("Debe ingresar el id del estudiante que desea actualizar.");
        }
        if(!estudianteDAO.existsById(estudiante.getIdEstudiante())){
            throw new Exception("No se encontró el estudiante con ese id.");
        }
        if(!avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())){
            throw new Exception("No existe un avatar con ese id.");
        }
        if(!generoDAO.existsById(estudiante.getGenero().getIdGenero())){
            throw new Exception("No existe un genero con ese id.");
        }
        if(!estadoDAO.existsById(estudiante.getEstado().getIdEstado())){
            throw new Exception("No existe un estado con ese id.");
        }
        if(!programaDAO.existsById(estudiante.getPrograma().getIdPrograma())){
            throw new Exception("No existe un programa con ese id.");
        }
        if(!semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())){
            throw new Exception("No existe un semestre con ese id.");
        }
        if(estudiante.getAvatar().getIdAvatar()<0){
            throw new Exception("Debe ingresar un id avatar válido.");
        }
        if(estudiante.getPrograma().getIdPrograma()<0){
            throw new Exception("Debe ingresar un id programa válido.");
        }
        if(estudiante.getEstado().getIdEstado()<0){
            throw new Exception("Debe ingresar un id estado válido.");
        }
        if(estudiante.getSemestre().getIdSemestre()<0){
            throw new Exception("Debe ingresar un id semestre válido.");
        }
        if(estudiante.getGenero().getIdGenero()<0){
            throw new Exception("Debe ingresar un id genero válido.");
        }
        if(estudiante.getNombre() == null || estudiante.getNombre().trim().equals("")){
            throw new Exception("Debe ingresar el nombre del estudiante.");
        }
        if(estudiante.getNombre().length()>80){
            throw new Exception("El nombre del estudiante es muy largo.");
        }
        if(estudiante.getApellido() == null || estudiante.getApellido().trim().equals("")){
            throw new Exception("Debe ingresar el apellido del estudiante.");
        }
        if(estudiante.getApellido().length()>80){
            throw new Exception("El apellido del estudiante es muy largo.");
        }
        if(estudiante.getNickName() == null || estudiante.getNickName().trim().equals("")){
            throw new Exception("Debe ingresar un nickname para el estudiante.");
        }
        if(estudiante.getNickName().length()>80){
            throw new Exception("Debe ingresar un nickname para el estudiante, no debe contener más de 80 caracteres.");
        }
        if(estudiante.getPuntaje()<0){
            throw new Exception("El puntaje no debe ser negativo.");
        }
        if(estudiante.getMonedasObtenidas()<0){
            throw new Exception("Las monedas obtenidas no debe ser negativo.");
        }
        if(estudiante.getCorreo() == null || estudiante.getCorreo().trim().equals("")){
            throw new Exception("Debe ingresar un correo del estudiante.");
        }
        if(!Validaciones.formatoCorreoValido(estudiante.getCorreo())) {
            throw new Exception("Debe ingresar un correo válido.");
        }
        if(estudiante.getFechaNacimiento() == null){
            throw new Exception("Debe ingresar una fecha de nacimiento.");
        }
        Date fechaActual = new Date();
        LocalDate fechaNamientoLocalDate = estudiante.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNamientoLocalDate, ahora);
        int edad = periodo.getYears();
/*
        if(edad < 15){
            throw new Exception("Digite una fecha de nacimiento válida, debe ser mayor de 15 años para poder registrarse.");
        }*/
        if(estudiante.getUsuarioModificador()==null || estudiante.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(estudiante.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.");
        }
        if(estudiante.getFechaModificacion()==null){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        /*if(estudiante.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }*/

    }
}
