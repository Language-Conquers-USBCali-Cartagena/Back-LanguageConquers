package com.example.demo.service.serviceImpl;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.MisionDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.model.Curso;
import com.example.demo.model.Estado;
import com.example.demo.model.Mision;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.service.EstadoService;
import com.example.demo.service.MisionService;
import com.example.demo.service.RetoService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class RetoServiceImpl implements RetoService {

    //TODO: Faltan validacones para los metodos de crear y actualizar
    @Autowired
    RetoDAO retoDAO;

    @Autowired
    MisionDAO misionDAO;

    @Autowired
    EstadoDAO estadoDAO;

    @Autowired
    CursoDAO cursoDAO;

    @Override
    public List<Reto> listReto() throws Exception{
        return retoDAO.findAll();
    }

    @Override
    public String registrar(Reto reto) throws Exception {
        try{
            validacionesCrear(reto);
            retoDAO.save(reto);
            return "Se creo exitosamente el reto.";
        }catch (Exception e){
            throw new Exception("EL reto no se creo.");
        }
    }

    @Override
    public String actualizar(RetoDTO retoDTO) throws Exception {
        Reto reto = null;
        Mision idMision = null;
        Estado idEstado = null;
        Curso idCurso = null;
        validacionesActualizar(retoDTO);
        reto = retoDAO.findById(retoDTO.getIdReto()).orElse(null);
        reto.setNombreReto(retoDTO.getNombreReto());
        reto.setDescripcion(retoDTO.getDescripcion());
        reto.setMaximoIntentos(retoDTO.getMaximoIntentos());
        reto.setFechaInicio(retoDTO.getFechaInicio());
        reto.setFechaLimite(retoDTO.getFechaLimite());
        reto.setEsGrupal(retoDTO.isEsGrupal());
        reto.setNrEstudiatesGrupo(retoDTO.getNrEstudiatesGrupo());
        reto.setMision(misionDAO.findById(retoDTO.getIdReto()).orElse(null));
        reto.setEstado(estadoDAO.findById(retoDTO.getIdEstado()).orElse(null));
        reto.setCurso(cursoDAO.findById(retoDTO.getIdCurso()).orElse(null));
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
        //TODO: FALTA VALIDACION DE SI EXISTE EN ORDEN_RETO_IDE, EN RETO_ESTUDIANTE Y EN ROL
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
        if(reto.getMaximoIntentos()<1){
            throw new Exception("El reto debe tener como mínimo 1 intento.");
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
        if(retoDTO.getMaximoIntentos()<1){
            throw new Exception("El reto debe tener como mínimo 1 intento.");
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
        if(retoDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }

    }
}
