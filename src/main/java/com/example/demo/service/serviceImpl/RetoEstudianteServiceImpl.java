package com.example.demo.service.serviceImpl;

import com.example.demo.dao.*;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RetoEstudianteDTO;
import com.example.demo.service.RetoEstudianteService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class RetoEstudianteServiceImpl implements RetoEstudianteService {
    @Autowired
    private RetoEstudianteDAO retoEstudianteDAO;

    @Autowired
    private RetoDAO retoDAO;

    @Autowired
    private EstadoDAO estadoDAO;

    @Autowired
    private GrupoDAO grupoDAO;

    @Autowired
    private RolDAO rolDAO;

    @Autowired
    private EstudianteDAO estudianteDAO;

    @Override
    public String crearRetoEstudiante(RetoEstudiante retoEstudiante) throws Exception {
        validacionesCrear(retoEstudiante);
        retoEstudianteDAO.save(retoEstudiante);
        return "Se creo exitosamente el reto estudiante!";
    }

    @Override
    public String actualizar(RetoEstudiante retoEstudiante) throws Exception {
        validacionesActualizar(retoEstudiante);
        retoEstudianteDAO.save(retoEstudiante);
        return "Se actualizo exitosamente el reto estudiante";
    }

    @Override
    public String eliminar(Long idRetoEstudiante) throws Exception {
        if(idRetoEstudiante == null){
            throw new Exception("El id del reto estudiante es obligatorio.");
        }
        if(!retoEstudianteDAO.existsById(idRetoEstudiante)){
            throw new Exception("No se encontró un reto estudiante con ese id.");
        }
        retoEstudianteDAO.deleteById(idRetoEstudiante);
        return "Se elimino exitosamente el reto estudiante";
    }

    @Override
    public List<RetoEstudiante> listar() throws Exception {
        return retoEstudianteDAO.findAll();
    }

    @Override
    public RetoEstudiante findById(Long idRetoEstudiante) throws Exception {
        if(idRetoEstudiante == null){
            throw new Exception("Debe ingresar el id del reto estudiante.");
        }
        if(!retoEstudianteDAO.existsById(idRetoEstudiante)){
            throw new Exception("El reto estudiante con id: " + idRetoEstudiante + " no existe.");
        }
        return retoEstudianteDAO.findById(idRetoEstudiante).get();
    }

    @Override
    public List<RetoEstudiante> listarPorIdEstudiante(Long idEstudiante) throws Exception {
        if(idEstudiante == null){
            throw new Exception("Debe ingresar el id del estudiante");
        }
        if(!estudianteDAO.existsById(idEstudiante)){
            throw new Exception("No se encontró un estudiante con ese id.");
        }
        if(retoEstudianteDAO.findByIdEstudiante(idEstudiante).isEmpty()){
            throw new Exception("No se encontraron retos realizados por ese estudiante");
        }
        return retoEstudianteDAO.findByIdEstudiante(idEstudiante);
    }

    @Override
    public List<RetoEstudiante> listarPorIdReto(Long idReto) throws Exception {
        if(idReto == null){
            throw new Exception("Debe ingresar el id del reto.");
        }
        if(!retoDAO.existsById(idReto)){
            throw new Exception("No se encontró un reto con ese id.");
        }
        if(retoEstudianteDAO.findByIdReto(idReto).isEmpty()){
            throw new Exception("No hay estudiantes que hayan realizado ese reto.");
        }
        return retoEstudianteDAO.findByIdReto(idReto);
    }

    @Override
    public RetoEstudiante findByIdRetoAndIdEstudiante(Long idReto, Long idEstudiante) throws Exception {
        if(!retoDAO.existsById(idReto)){
            throw new Exception("No existe reto con ese id.");
        }
        if(!estudianteDAO.existsById(idEstudiante)){
            throw new Exception("No existe estudiante con ese id.");
        }
        return retoEstudianteDAO.findByIdRetoAndIdEstuduante(idReto, idEstudiante);

    }

    @Override
    public int promedioRetosCompletadosEstudiantes() throws Exception {
        int promedioRetos = retoEstudianteDAO.promedioRetosCompletadosEstudiantes();
        return promedioRetos;
    }

    private void validacionesCrear(RetoEstudiante retoEstudiante)throws Exception{
        Date fechaActual = new Date();
        if(retoEstudiante.getFechaEntrega() == null){
            throw new Exception("Se debe ingresar una fecha de entrega.");
        }
        if(retoEstudiante.getFechaEntrega().compareTo(fechaActual)>0){
            throw new Exception("No se puede asignar una fecha de entrega que aun no ha sucedido.");
        }
        // Convertir la fecha a validar a Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(retoEstudiante.getFechaEntrega());

        // Restar 6 meses a la fecha a validar
        calendar.add(Calendar.MONTH, -6);
        Date fechaLimite = calendar.getTime();


        // no puede haber ocurrido hace mas de 6 meses
        if(!fechaLimite.after(fechaActual)){
            throw new Exception("La fecha de entrega no debe ser menor a 6 meses");
        }
        //TODO: REVISAR LO DEL PUNTAJE
        if(retoEstudiante.getPuntaje() <0){
            throw new Exception("No se puede asignar un puntaje negativo al reto estudiante.");
        }
        if(retoEstudiante.getEstado().getIdEstado() == null){
            throw new Exception("Debe ingresar un idEstado.");
        }
        if(retoEstudiante.getEstudiante().getIdEstudiante() == null){
            throw new Exception("Debe ingresar un idEstudiante");
        }
        if(retoEstudiante.getReto().getIdReto() == null){
            throw new Exception("Debe ingresar un idReto.");
        }
        if(retoEstudiante.getEstado().getIdEstado()<0){
            throw new Exception("Debe ingresar un idEstado válido");
        }
        if(retoEstudiante.getEstudiante().getIdEstudiante()<0){
            throw new Exception("Debe ingresar un idEstudiante válido.");
        }
        if(retoEstudiante.getReto().getIdReto()<0){
            throw new Exception("Debe ingresar un idReto válido.");
        }
        if(estadoDAO.findById(retoEstudiante.getEstado().getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un idEstado válido");
        }
        if(estudianteDAO.findById(retoEstudiante.getEstudiante().getIdEstudiante()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un idEstudiante válido.");
        }
        if(retoDAO.findById(retoEstudiante.getReto().getIdReto()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un udReto válido.");
        }
        if(retoEstudiante.getUsuarioCreador() == null || retoEstudiante.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario creador.");
        }
        if(Validaciones.isStringLenght(retoEstudiante.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo se aceptan 50 caracteres.");
        }
        if(retoEstudiante.getFechaCreacion() == null || retoEstudiante.getFechaCreacion().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
        if(retoEstudiante.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No se puede asignar una fecha de creación que aun no ha sucedido.");
        }

    }

    private void validacionesActualizar(RetoEstudiante retoEstudiante)throws Exception{
        Date fechaActual = new Date();
        if(retoEstudiante.getFechaEntrega() == null){
            throw new Exception("Se debe ingresar una fecha de entrega.");
        }
//        if(retoEstudiante.getFechaEntrega().compareTo(fechaActual)>0){
//            throw new Exception("No se puede asignar una fecha de entrega que aun no ha sucedido.");
//        }
        // Convertir la fecha a validar a Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(retoEstudiante.getFechaEntrega());

        // Restar 6 meses a la fecha a validar
        calendar.add(Calendar.MONTH, -6);
        Date fechaLimite = calendar.getTime();

        // no puede haber ocurrido hace mas de 6 meses
//        if(!fechaLimite.after(fechaActual)){
//            throw new Exception("La fecha de entrega no debe ser menor a 6 meses");
//        }
        //TODO: REVISAR LO DEL PUNTAJE
        if(retoEstudiante.getPuntaje() <0){
            throw new Exception("No se puede asignar un puntaje negativo al reto estudiante.");
        }
        if(retoEstudiante.getEstado().getIdEstado() == null){
            throw new Exception("Debe ingresar un idEstado.");
        }
        if(retoEstudiante.getEstudiante().getIdEstudiante() == null){
            throw new Exception("Debe ingresar un idEstudiante");
        }
        if(retoEstudiante.getReto().getIdReto() == null){
            throw new Exception("Debe ingresar un idReto.");
        }
        if(retoEstudiante.getEstado().getIdEstado()<0){
            throw new Exception("Debe ingresar un idEstado válido");
        }
        if(retoEstudiante.getEstudiante().getIdEstudiante()<0){
            throw new Exception("Debe ingresar un idEstudiante válido.");
        }
        if(retoEstudiante.getReto().getIdReto()<0){
            throw new Exception("Debe ingresar un idReto válido.");
        }
        if(estadoDAO.findById(retoEstudiante.getEstado().getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un idEstado válido");
        }
        if(estudianteDAO.findById(retoEstudiante.getEstudiante().getIdEstudiante()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un idEstudiante válido.");
        }
        if(retoDAO.findById(retoEstudiante.getReto().getIdReto()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un udReto válido.");
        }
        if(retoEstudiante.getUsuarioModificador() == null || retoEstudiante.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario modificador.");
        }
        if(Validaciones.isStringLenght(retoEstudiante.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo se aceptan 50 caracteres.");
        }
        if(retoEstudiante.getFechaModificacion() == null || retoEstudiante.getFechaModificacion().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        if(retoEstudiante.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No se puede asignar una fecha de modificación que aun no ha sucedido.");
        }

    }

}
