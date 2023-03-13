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

    //TODO: FALTA VALIDACION de crear y actualizar DE LOS NUEVOS ATRIBUTOS DE SOLUCION Y MONEDAS
    @Override
    public String registrar(Reto reto) throws Exception {
        validacionesCrear(reto);
        retoDAO.save(reto);
        return "Se creo exitosamente el reto.";
    }

    @Override
    public String actualizar(RetoDTO retoDTO) throws Exception {
        Reto reto = null;
        validacionesActualizar(retoDTO);
        reto = retoDAO.findById(retoDTO.getIdReto()).orElse(null);
        reto.setNombreReto(retoDTO.getNombreReto());
        reto.setDescripcion(retoDTO.getDescripcion());
        reto.setMaximoIntentos(retoDTO.getMaximoIntentos());
        reto.setFechaInicio(retoDTO.getFechaInicio());
        reto.setFechaLimite(retoDTO.getFechaLimite());
        reto.setEsGrupal(retoDTO.isEsGrupal());
        reto.setMoneda(retoDTO.getMoneda());
        reto.setSolucion(retoDTO.getSolucion());
        reto.setNrEstudiatesGrupo(retoDTO.getNrEstudiatesGrupo());
        reto.setMision(misionDAO.findById(retoDTO.getIdMision()).orElse(null));
        reto.setEstado(estadoDAO.findById(retoDTO.getIdEstado()).orElse(null));
        reto.setCurso(cursoDAO.findById(retoDTO.getIdCurso()).orElse(null));
        reto.setFechaModificacion(retoDTO.getFechaModificacion());
        reto.setUsuarioModificador(retoDTO.getUsuarioModificador());
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
    public String habilitarReto(RetoDTO retoDTO) throws Exception {
        Reto reto = null;
        validacionesActualizar(retoDTO);
        reto = retoDAO.findById(retoDTO.getIdReto()).orElse(null);
        reto.setNombreReto(retoDTO.getNombreReto());
        reto.setDescripcion(retoDTO.getDescripcion());
        reto.setMaximoIntentos(retoDTO.getMaximoIntentos());
        reto.setFechaInicio(retoDTO.getFechaInicio());
        reto.setFechaLimite(retoDTO.getFechaLimite());
        reto.setEsGrupal(retoDTO.isEsGrupal());
        reto.setMoneda(retoDTO.getMoneda());
        reto.setSolucion(retoDTO.getSolucion());
        reto.setNrEstudiatesGrupo(retoDTO.getNrEstudiatesGrupo());
        reto.setMision(misionDAO.findById(retoDTO.getIdMision()).orElse(null));
        reto.setEstado(estadoDAO.findById(retoDTO.getIdEstado()).orElse(null));
        reto.setCurso(cursoDAO.findById(retoDTO.getIdCurso()).orElse(null));
        reto.setFechaModificacion(retoDTO.getFechaModificacion());
        reto.setUsuarioModificador(retoDTO.getUsuarioModificador());
        retoDAO.save(reto);
        return "Se actualizo el reto.";
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
        if(reto.getMoneda()<=0){
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
        if(reto.getImagenTema2() != null && Validaciones.isStringLenght(reto.getImagenTema2(),300)){
            throw new Exception("La Url de la imagen 2 es muy larga.");
        }
        if(reto.getUrlVideo1() != null && Validaciones.isStringLenght(reto.getUrlVideo1(), 300)){
            throw new Exception("La Url del video 1 es muy largo.");
        }
        if(reto.getUrlVideo2() != null && Validaciones.isStringLenght(reto.getUrlVideo2(), 300)){
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

    private void validacionesActualizar(RetoDTO retoDTO)throws Exception{
        if(retoDTO.getIdReto() == null){
            throw new Exception("Se debe ingresar el id del reto que se va a actualizar.");
        }
        if(!retoDAO.existsById(retoDTO.getIdReto())){
            throw new Exception("No existe un reto con ese id.");
        }
        if(retoDTO.getNombreReto() == null || retoDTO.getNombreReto().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del reto.");
        }
        if(Validaciones.isStringLenght(retoDTO.getNombreReto(),50)){
            throw new Exception("El nombre del reto no debe contener más de 50 caracteres.");
        }
        if(retoDTO.getDescripcion() == null || retoDTO.getDescripcion().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción para el reto.");
        }
        if(Validaciones.isStringLenght(retoDTO.getDescripcion(), 300)){
            throw new Exception("La descripción del reto no debe superar los 300 caracteres.");
        }
        if(retoDTO.isEsGrupal() && retoDTO.getNrEstudiatesGrupo()<2){
            throw new Exception("El número de estudiantes por grupo no puede ser menor a 2 si es un reto grupal.");
        }
        if(retoDTO.getDescripcionTeoria() == null || retoDTO.getDescripcionTeoria().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción de la teoría referente al reto.");
        }
        if(Validaciones.isStringLenght(retoDTO.getDescripcionTeoria(), 10000)){
            throw new Exception("La descripción de la teoría no debe superar los 1000 caracteres.");
        }
        if(retoDTO.getMaximoIntentos()<1){
            throw new Exception("El reto debe tener como mínimo 1 intento.");
        }
        if(retoDTO.getMoneda()<=0){
            throw new Exception("Debe asignarle una cantidad de monedas al reto.");
        }
        if(retoDTO.getSolucion() == null){
            throw new Exception("Debe ingresar una solucion al reto.");
        }
        if(Validaciones.isStringLenght(retoDTO.getSolucion(),800) ){
            throw new Exception("La solución no debe superar los 800 caracteres.");
        }
        if(retoDTO.getImagen1() != null && Validaciones.isStringLenght(retoDTO.getImagen1(),300)){
            throw new Exception("La Url de la imagen 1 es muy larga.");
        }
        if(retoDTO.getImagen2() != null && Validaciones.isStringLenght(retoDTO.getImagen2(),300)){
            throw new Exception("La Url de la imagen 2 es muy larga.");
        }
        if(retoDTO.getUrlVideo1() != null && Validaciones.isStringLenght(retoDTO.getUrlVideo1(), 300)){
            throw new Exception("La Url del video 1 es muy largo.");
        }
        if(retoDTO.getUrlVideo2() != null && Validaciones.isStringLenght(retoDTO.getUrlVideo2(), 300)){
            throw new Exception("La Url del video 1 es muy largo.");
        }
        if(retoDTO.getIdEstado() == null){
            throw new Exception("Se debe ingresar un id estado.");
        }
        if(retoDTO.getIdEstado()<0){
            throw new Exception("Se debe ingresar un id estado válido.");
        }
        if(estadoDAO.findById(retoDTO.getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id estado válido.");
        }
        if(retoDTO.getIdCurso() == null){
            throw new Exception("Se debe ingresar un id curso.");
        }
        if(retoDTO.getIdCurso()<0){
            throw new Exception("Se debe ingresar un id curso válido.");
        }
        if(cursoDAO.findById(retoDTO.getIdCurso()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id curso válido.");
        }
        if(retoDTO.getIdMision() == null){
            throw new Exception("Se debe ingresar un id misión.");
        }
        if(retoDTO.getIdMision()<0){
            throw new Exception("Se debe ingresar un id misión válido.");
        }
        if(misionDAO.findById(retoDTO.getIdMision()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id misión válido.");
        }
        if(retoDTO.getUsuarioModificador()==null || retoDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(retoDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(retoDTO.getFechaModificacion()==null ||retoDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date fechaMaxima = calendar.getTime();
        if(retoDTO.getFechaInicio().compareTo(fechaActual)<0){
            throw new Exception("No se puede configurar la fecha de inicio del reto con una fecha que ya paso.");
        }
        if(retoDTO.getFechaLimite().compareTo(fechaActual)<0){
            throw new Exception("No se puede configurar la fecha limite del reto con una fecha que ya paso.");
        }
        if(retoDTO.getFechaLimite().before(fechaMaxima)){
            throw new Exception("La fecha limite no puede superar los 6 meses.");
        }
        if(retoDTO.getFechaLimite().before(retoDTO.getFechaInicio())){
            throw new Exception("La fecha limite no puede ser menor que la fecha de inicio del reto.");
        }
        if(retoDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }

    }

    private void validacionesHabilitarReto(RetoDTO retoDTO)throws Exception{
        if(retoDTO.getIdReto() == null){
            throw new Exception("Se debe ingresar el id del reto que se va a actualizar.");
        }
        if(!retoDAO.existsById(retoDTO.getIdReto())){
            throw new Exception("No existe un reto con ese id.");
        }
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date fechaMaxima = calendar.getTime();
        if(retoDTO.getFechaInicio().compareTo(fechaActual)<0){
            throw new Exception("No se puede configurar la fecha de inicio del reto con una fecha que ya paso.");
        }
        if(retoDTO.getFechaLimite().compareTo(fechaActual)<0){
            throw new Exception("No se puede configurar la fecha limite del reto con una fecha que ya paso.");
        }
        if(retoDTO.getFechaLimite().before(fechaMaxima)){
            throw new Exception("La fecha limite no puede superar los 6 meses.");
        }
        if(retoDTO.getFechaLimite().before(retoDTO.getFechaInicio())){
            throw new Exception("La fecha limite no puede ser menor que la fecha de inicio del reto.");
        }
        if(retoDTO.getMaximoIntentos()<1){
            throw new Exception("El reto debe tener como mínimo 1 intento.");
        }
        if(retoDTO.getMoneda()<=0){
            throw new Exception("Debe asignarle una cantidad de monedas al reto.");
        }
        if(retoDTO.getIdEstado() == null){
            throw new Exception("Se debe ingresar un id estado.");
        }
        if(retoDTO.getIdEstado()<0){
            throw new Exception("Se debe ingresar un id estado válido.");
        }
        if(estadoDAO.findById(retoDTO.getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Se debe ingresar un id estado válido.");
        }
    }
}
