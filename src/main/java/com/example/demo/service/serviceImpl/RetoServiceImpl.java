package com.example.demo.service.serviceImpl;

import com.example.demo.dao.*;
import com.example.demo.model.*;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.service.EstadoService;
import com.example.demo.service.MisionService;
import com.example.demo.service.RetoService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class RetoServiceImpl implements RetoService {


    @Autowired
    RetoDAO retoDAO;

    @Autowired
    MisionDAO misionDAO;

    @Autowired
    EstadoDAO estadoDAO;

    @Autowired
    CursoDAO cursoDAO;

    @Autowired
    RetoEstudianteDAO retoEstudianteDAO;

    @Autowired
    RolDAO rolDAO;

    @Override
    public List<Reto> listReto() throws Exception{
        return retoDAO.findAll();
    }

    @Override
    public String registrar(Reto reto) throws Exception {
        validacionesCrear(reto);
        retoDAO.save(reto);
        return "Se creo exitosamente el reto.";
    }

    @Override
    public String actualizar(Reto reto) throws Exception {
        validacionesActualizar(reto);

        retoDAO.save(reto);
        return "Se actualizo el reto.";
    }

    @Override
    public String eliminar(Long idReto) throws Exception {
        if(idReto == null){
            throw new Exception("El id del reto no existe.");
        }
        if(!retoDAO.existsById(idReto)){
            throw new Exception("No se encontró un reto con el id: " + idReto+".");
        }
        if(!retoEstudianteDAO.findByIdReto(idReto).isEmpty()){
            throw new Exception("No se puede eliminar el reto porque esta siendo utilizado por un reto estudiante.");
        }
        if(!rolDAO.findByIdReto(idReto).isEmpty()){
            throw new Exception("No se puede eliminar el reto porque esta siendo utilizado por un rol.");
        }
        retoDAO.deleteById(idReto);
        return "El reto se elimino exitosamente.";
    }

    @Override
    public Reto findById(Long idReto) throws Exception {
        if(idReto == null){
            throw new Exception("Debe ingresar el id de un reto.");
        }
        if(!retoDAO.existsById(idReto)){
            throw new Exception("El reto con id: " + idReto + " no existe.");
        }
        return retoDAO.findById(idReto).get();
    }

    @Override
    public int promedioMonedasRetos() throws Exception {
        int promedioMonedasPorReto = retoDAO.promedioMonedasRetos();
        return promedioMonedasPorReto;
    }

    @Override
    public int retosRegistrados() throws Exception {
        int retosRegistrados = retoDAO.retosRegistrados();
        return retosRegistrados;
    }


    private void validacionesCrear(Reto reto) throws Exception{
        if(reto.getNombreReto() == null || reto.getNombreReto().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del reto.");
        }
        if(Validaciones.isStringLenght(reto.getNombreReto(),50)){
            throw new Exception("El nombre del reto no debe contener más de 50 caracteres.");
        }
        if(reto.getDescripcion() == null || reto.getDescripcion().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción para el reto.");
        }
        if(Validaciones.isStringLenght(reto.getDescripcion(), 300)){
            throw new Exception("La descripción del reto no debe superar los 300 caracteres.");
        }

        if(reto.isEsGrupal() && reto.getNrEstudiatesGrupo()<2){
            throw new Exception("El número de estudiantes por grupo no puede ser menor a 2 si es un reto grupal.");
        }
        if(reto.getDescripcionTeoria() == null || reto.getDescripcionTeoria().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción de la teoría referente al reto.");
        }
        if(Validaciones.isStringLenght(reto.getDescripcionTeoria(), 10000)){
            throw new Exception("La descripción de la teoría no debe superar los 1000 caracteres.");
        }
        if(reto.getMaximoIntentos()<1){
            throw new Exception("El reto debe tener como mínimo 1 intento.");
        }
        if(reto.getMoneda()<0){
            throw new Exception("Debe asignarle una cantidad de monedas al reto.");
        }
        if(reto.getSolucion() == null){
            throw new Exception("Se debe ingresar la solución del reto.");
        }
        if(Validaciones.isStringLenght(reto.getSolucion(),800) ){
            throw new Exception("La solución no debe superar los 800 caracteres.");
        }

        if(reto.getImagenTema1() != null && Validaciones.isStringLenght(reto.getImagenTema1(),300)){
            throw new Exception("La Url de la imagen 1 es muy larga.");
        }
        if(reto.getImagenTema2() != null && Validaciones.isStringLenght(reto.getImagenTema2(),300)){
            throw new Exception("La Url de la imagen 2 es muy larga.");
        }
        if(reto.getUrlVideo1() != null  && Validaciones.isStringLenght(reto.getUrlVideo1(), 300)){
            throw new Exception("La Url del video 1 es muy largo.");
        }
        if(reto.getUrlVideo2() != null  && Validaciones.isStringLenght(reto.getUrlVideo2(), 300)){
            throw new Exception("La Url del video 1 es muy largo.");
        }
        if(reto.getEstado().getIdEstado() == null){
            throw new Exception("Se debe ingresar un id estado.");
        }
        if(reto.getEstado().getIdEstado()<0){
            throw new Exception("Se debe ingresar un id estado válido.");
        }
        if(estadoDAO.findById(reto.getEstado().getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id estado válido.");
        }
        if(reto.getCurso().getIdCurso() == null){
            throw new Exception("Se debe ingresar un id curso.");
        }
        if(reto.getCurso().getIdCurso()<0){
            throw new Exception("Se debe ingresar un id curso válido.");
        }
        if(cursoDAO.findById(reto.getCurso().getIdCurso()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id curso válido.");
        }
        if(reto.getMision().getIdMision() == null){
            throw new Exception("Se debe ingresar un id misión.");
        }
        if(reto.getMision().getIdMision()<0){
            throw new Exception("Se debe ingresar un id misión válido.");
        }
        if(misionDAO.findById(reto.getMision().getIdMision()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id misión válido.");
        }
        if(reto.getUsuarioCreador()==null || reto.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(reto.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(reto.getFechaCreacion()==null ||reto.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date fechaMaxima = calendar.getTime();
        if(reto.getFechaInicio().compareTo(fechaActual)<0){
            throw new Exception("No se puede configurar la fecha de inicio del reto con una fecha que ya paso.");
        }
        if(reto.getFechaLimite().compareTo(fechaActual)<0){
            throw new Exception("No se puede configurar la fecha limite del reto con una fecha que ya paso.");
        }
        if(reto.getFechaLimite().before(fechaMaxima)){
            throw new Exception("La fecha limite no puede superar los 6 meses.");
        }
        if(reto.getFechaLimite().before(reto.getFechaInicio())){
            throw new Exception("La fecha limite no puede ser menor que la fecha de inicio del reto.");
        }
        if(reto.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }

    private void validacionesActualizar(Reto reto)throws Exception{
        if(reto.getIdReto() == null){
            throw new Exception("Se debe ingresar el id del reto que se va a actualizar.");
        }
        if(!retoDAO.existsById(reto.getIdReto())){
            throw new Exception("No existe un reto con ese id.");
        }
        if(reto.getNombreReto() == null || reto.getNombreReto().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del reto.");
        }
        if(Validaciones.isStringLenght(reto.getNombreReto(),50)){
            throw new Exception("El nombre del reto no debe contener más de 50 caracteres.");
        }
        if(reto.getDescripcion() == null || reto.getDescripcion().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción para el reto.");
        }
        if(Validaciones.isStringLenght(reto.getDescripcion(), 300)){
            throw new Exception("La descripción del reto no debe superar los 300 caracteres.");
        }
        if(reto.isEsGrupal() && reto.getNrEstudiatesGrupo()<2){
            throw new Exception("El número de estudiantes por grupo no puede ser menor a 2 si es un reto grupal.");
        }
        if(reto.getDescripcionTeoria() == null || reto.getDescripcionTeoria().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción de la teoría referente al reto.");
        }
        if(Validaciones.isStringLenght(reto.getDescripcionTeoria(), 10000)){
            throw new Exception("La descripción de la teoría no debe superar los 1000 caracteres.");
        }
        if(reto.getMaximoIntentos()<1){
            throw new Exception("El reto debe tener como mínimo 1 intento.");
        }
        if(reto.getMoneda()<0){
            throw new Exception("Debe asignarle una cantidad de monedas al reto.");
        }
        if(reto.getSolucion() == null){
            throw new Exception("Debe ingresar una solucion al reto.");
        }
        if(Validaciones.isStringLenght(reto.getSolucion(),800) ){
            throw new Exception("La solución no debe superar los 800 caracteres.");
        }
        if(reto.getImagenTema1() != null && Validaciones.isStringLenght(reto.getImagenTema1(),300)){
            throw new Exception("La Url de la imagen 1 es muy larga.");
        }
        if(reto.getImagenTema2() != null  && Validaciones.isStringLenght(reto.getImagenTema2(),300)){
            throw new Exception("La Url de la imagen 2 es muy larga.");
        }
        if(reto.getUrlVideo1() != null  && Validaciones.isStringLenght(reto.getUrlVideo1(), 300)){
            throw new Exception("La Url del video 1 es muy largo.");
        }
        if(reto.getUrlVideo2() != null  && Validaciones.isStringLenght(reto.getUrlVideo2(), 300)){
            throw new Exception("La Url del video 2 es muy largo.");
        }
        if(reto.getEstado() == null){
            throw new Exception("Se debe ingresar un id estado.");
        }
        if(reto.getEstado().getIdEstado()<0){
            throw new Exception("Se debe ingresar un id estado válido.");
        }
        if(reto.getEstado().toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id estado válido.");
        }
        if(reto.getCurso().getIdCurso() == null){
            throw new Exception("Se debe ingresar un id curso.");
        }
        if(reto.getCurso().getIdCurso()<0){
            throw new Exception("Se debe ingresar un id curso válido.");
        }
        if(reto.getCurso().toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id curso válido.");
        }
        if(reto.getMision() == null){
            throw new Exception("Se debe ingresar un id misión.");
        }
        if(reto.getMision().getIdMision()<0){
            throw new Exception("Se debe ingresar un id misión válido.");
        }
        if(reto.getMision().toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id misión válido.");
        }
        if(reto.getUsuarioModificador()==null || reto.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(reto.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(reto.getFechaModificacion()==null ||reto.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date fechaMaxima = calendar.getTime();
        if(reto.getFechaInicio().compareTo(fechaActual)<0){
            throw new Exception("No se puede configurar la fecha de inicio del reto con una fecha que ya paso.");
        }
        if(reto.getFechaLimite().compareTo(fechaActual)<0){
            throw new Exception("No se puede configurar la fecha limite del reto con una fecha que ya paso.");
        }
        if(reto.getFechaLimite().before(fechaMaxima)){
            throw new Exception("La fecha limite no puede superar los 6 meses.");
        }
        if(reto.getFechaLimite().before(reto.getFechaInicio())){
            throw new Exception("La fecha limite no puede ser menor que la fecha de inicio del reto.");
        }
        if(reto.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }

    }


}
