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
            return "Se pudo crear correctamente el nivel misión";
        }catch (Exception e){
            throw new Exception("No se puede crear el nivel misión");
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
        nivelMision.setFechaCreacion(nivelMisionDTO.getFechaCreacion());
        nivelMision.setUsuarioModificador(nivelMisionDTO.getUsuarioModificador());
        nivelMisionDAO.save(nivelMision);
        return "Se actualizo correctamente el nivelMision";
    }

    @Override
    public String eliminar(Long idNivelMision) throws Exception {
        if(idNivelMision == null){
            throw new Exception("Se debe ingresar el id del nivel misión que desea eliminar. ");
        }
        if(!nivelMisionDAO.existsById(idNivelMision)){
            throw new Exception("No se encontro el nivelMision con el id: " + idNivelMision);
        }
        nivelMisionDAO.deleteById(idNivelMision);
        return "Se elimino el nivel mision correctamente!";
    }

    @Override
    public NivelMision findById(Long idNivelMision) throws Exception {
        if(idNivelMision == null){
            throw new Exception("Se debe ingresar el id del ");
        }
        if(!nivelMisionDAO.existsById(idNivelMision)){
            throw new Exception("No se encontro el nivelMision con el id: " + idNivelMision);
        }
        return nivelMisionDAO.findById(idNivelMision).get();
    }

    private void validacionesCrear(NivelMision nivelMision) throws Exception{

        if(nivelMision.getNombre() == null || nivelMision.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del Nivel Misión.");
        }
        if(Validaciones.isStringLenght(nivelMision.getNombre(), 50)){
            throw new Exception("El nombre del Nivel Misión es muy largo.");
        }
        if(nivelMision.getImgNivelMision() == null || nivelMision.getImgNivelMision().equals("")){
            throw new Exception("Debe ingresar una imagen para el Nivel Misión.");
        }
        if(Validaciones.isStringLenght(nivelMision.getImgNivelMision(), 80)){
            throw new Exception("El nombre de la imagen es muy largo.");
        }
        if(Validaciones.validExtensionImg(nivelMision.getImgNivelMision())){
            throw new Exception("Debe ingresar un formato valido de imagen. Se aceptan formatos PNG, JPG, TIFF, JPGE, GIF y SVG.");
        }
        if(nivelMision.getPuntajeMinimo()<0){
            throw new Exception("El puntaje minimo no debe ser menor a cero");
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
    }

    private void validacionesActualizar(NivelMisionDTO nivelMisionDTO) throws Exception{
        if(nivelMisionDTO.getIdNivelMision() == null){
            throw new Exception("Debe ingresar el ID del Nivel Misión que desea actualizar.");
        }
        if(!nivelMisionDAO.existsById(nivelMisionDTO.getIdNivelMision())){
            throw new Exception("No se encontro el nivel misión con es ID.");
        }
        if(nivelMisionDTO.getNombre() == null || nivelMisionDTO.getNombre().equals("")){
            throw new Exception("Debe ingresar el nombre del Nivel Misión.");
        }
        if(Validaciones.isStringLenght(nivelMisionDTO.getNombre(), 50)){
            throw new Exception("El nombre del Nivel Misión es muy largo.");
        }
        if(nivelMisionDTO.getImgNivelMision() == null || nivelMisionDTO.getImgNivelMision().equals("")){
            throw new Exception("Debe ingresar una imagen para el Nivel Misión.");
        }
        if(Validaciones.isStringLenght(nivelMisionDTO.getImgNivelMision(), 80)){
            throw new Exception("El nombre de la imagen es muy largo.");
        }
        if(Validaciones.validExtensionImg(nivelMisionDTO.getImgNivelMision())){
            throw new Exception("Debe ingresar un formato valido de imagen. Se aceptan formatos PNG, JPG, TIFF, JPGE, GIF y SVG.");
        }
        if(nivelMisionDTO.getPuntajeMinimo()<0){
            throw new Exception("El puntaje minimo no debe ser menor a cero");
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
