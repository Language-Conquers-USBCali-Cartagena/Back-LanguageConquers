package com.example.demo.service.serviceImpl;

import com.example.demo.dao.NivelMisionDAO;
import com.example.demo.model.NivelMision;
import com.example.demo.model.dto.NivelMisionDTO;
import com.example.demo.service.NivelMisionService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class NivelMisionServiceImpl implements NivelMisionService {
    //TODO: TERMINAR DE CREAR LOS SERVICIOS BASICOS COMO EDITAR Y ELIMINAR
    @Autowired
    NivelMisionDAO nivelMisionDAO;


    @Override
    public List<NivelMision> findAll() throws Exception {
        return nivelMisionDAO.findAll();
    }

    @Override
    public String Registrar(NivelMision nivelMision) throws Exception {
        try{
            validacionesCrear(nivelMision);
            nivelMisionDAO.save(nivelMision);
            return "Se creo exitosamente el nivel misión.";
        }catch (Exception e){
            throw new Exception("No se creo el nivel misión.");
        }
    }

    @Override
    public String actualizar(NivelMisionDTO nivelMisionDTO) throws Exception {
        NivelMision nivelMision = null;
        validacionesActualizar(nivelMisionDTO);
        nivelMision = nivelMisionDAO.findById(nivelMisionDTO.getIdNivelMision()).orElse(null);
        nivelMision.setNombre(nivelMisionDTO.getNombre());
        nivelMision.setPuntajeMinimo(nivelMisionDTO.getPuntajeMinimo());
        nivelMision.setUsuarioCreador(nivelMisionDTO.getUsuarioCreador());
        nivelMision.setFechaModificacion(nivelMisionDTO.getFechaModificacion());
        nivelMision.setUsuarioModificador(nivelMisionDTO.getUsuarioModificador());
        nivelMisionDAO.save(nivelMision);
        return "Se actualizo correctamente el nivel misión.";
    }

    @Override
    public String eliminar(Long idNivelMision) throws Exception {
        if(idNivelMision == null){
            throw new Exception("Se debe ingresar el id del nivel misión que desea eliminar. ");
        }
        if(!nivelMisionDAO.existsById(idNivelMision)){
            throw new Exception("No se encontró el nivel misión con el id: " + idNivelMision +".");
        }
        nivelMisionDAO.deleteById(idNivelMision);
        return "Se elimino el nivel misión correctamente.";
    }

    @Override
    public NivelMision findById(Long idNivelMision) throws Exception {
        if(idNivelMision == null){
            throw new Exception("Se debe ingresar el id del nivel misión.");
        }
        if(!nivelMisionDAO.existsById(idNivelMision)){
            throw new Exception("No se encontró el nivel misión con el id: " + idNivelMision+ ".");
        }
        return nivelMisionDAO.findById(idNivelMision).get();
    }

    private void validacionesCrear(NivelMision nivelMision) throws Exception{

        if(nivelMision.getNombre() == null || nivelMision.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del nivel misión.");
        }
        if(Validaciones.isStringLenght(nivelMision.getNombre(), 50)){
            throw new Exception("El nombre del nivel misión es muy largo.");
        }
        if(nivelMision.getPuntajeMinimo()<0){
            throw new Exception("El puntaje mínimo es cero.");
        }
        if(nivelMision.getUsuarioCreador() == null || nivelMision.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario creador.");
        }
        if(Validaciones.isStringLenght(nivelMision.getUsuarioCreador(), 50)){
            throw new Exception("El nombre del usuario creador es muy largo.");
        }
        if(nivelMision.getFechaCreacion() == null || nivelMision.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
        Date fechaActual = new Date();
        if(nivelMision.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }

    private void validacionesActualizar(NivelMisionDTO nivelMisionDTO) throws Exception{
        if(nivelMisionDTO.getIdNivelMision() == null){
            throw new Exception("Debe ingresar el id del nivel misión que desea actualizar.");
        }
        if(!nivelMisionDAO.existsById(nivelMisionDTO.getIdNivelMision())){
            throw new Exception("No se encontró el nivel misión con es id.");
        }
        if(nivelMisionDTO.getNombre() == null || nivelMisionDTO.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del nivel misión.");
        }
        if(Validaciones.isStringLenght(nivelMisionDTO.getNombre(), 50)){
            throw new Exception("El nombre del nivel misión es muy largo.");
        }
        if(nivelMisionDTO.getPuntajeMinimo()<0){
            throw new Exception("El puntaje mínimo no debe ser menor a cero");
        }
        if(nivelMisionDTO.getUsuarioModificador() == null || nivelMisionDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario modificador.");
        }
        if(Validaciones.isStringLenght(nivelMisionDTO.getUsuarioModificador(), 50)){
            throw new Exception("El nombre del usuario modificador es muy largo.");
        }
        if(nivelMisionDTO.getFechaModificacion() == null || nivelMisionDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        Date fechaActual = new Date();
        if(nivelMisionDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha modificación que aun no ha sucedido.");
        }

    }
}
