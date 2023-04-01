package com.example.demo.service.serviceImpl;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;
import com.example.demo.service.ArticulosAdquiridosService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class ArticulosAdquiridosServiceImpl implements ArticulosAdquiridosService {

    @Autowired
    ArticulosAdquiridosDAO articulosAdquiridosDAO;

    @Autowired
    ArticulosDAO articulosDAO;

    @Autowired
    EstudianteDAO estudianteDAO;

    @Override
    public String registrar(ArticulosAdquiridos articulosAdquiridos) throws Exception {
        validacionesCrear(articulosAdquiridos);
        articulosAdquiridosDAO.save(articulosAdquiridos);
        return "Se creo correctamente el artículo adquirido.";
    }

    @Override
    public String actualizar(ArticulosAdquiridosDTO articulosAdquiridosDTO) throws Exception {
        ArticulosAdquiridos articulosAdquiridos = null;
        validacionesActualizar(articulosAdquiridosDTO);
        articulosAdquiridos = articulosAdquiridosDAO.findById(articulosAdquiridosDTO.getIdArticuloAdquirido()).orElse(null);
        articulosAdquiridos.setEstudiante(estudianteDAO.findById(articulosAdquiridosDTO.getIdEstudiante()).orElse(null));
        articulosAdquiridos.setArticulos((articulosDAO.findById(articulosAdquiridosDTO.getIdArticulos()).orElse(null)));
        articulosAdquiridos.setUsuarioModificador(articulosAdquiridosDTO.getUsuarioModificador());
        articulosAdquiridos.setFechaModificacion(articulosAdquiridosDTO.getFechaModificacion());
        articulosAdquiridosDAO.save(articulosAdquiridos);
        return "Se actualizo correctamente el artículo adquirido.";
    }

    @Override
    public String eliminar(Long idArticuloA) throws Exception {
        if(idArticuloA == null){
            throw new Exception("Se debe ingresar el id del artículo adquirido.");
        }
        if(!articulosAdquiridosDAO.existsById(idArticuloA)){
            throw new Exception("El artrículo adquirido con id: " + idArticuloA + " no existe.");
        }
        articulosAdquiridosDAO.deleteById(idArticuloA);
        return "El artículo adquirido se ha eliminado exitosamente.";
    }

    @Override
    public ArticulosAdquiridos findById(Long idArticuloA) throws Exception {
        if(idArticuloA == null){
            throw new Exception("Se debe ingresar el id del artículo adquirido.");
        }
        if(!articulosAdquiridosDAO.existsById(idArticuloA)){
            throw new Exception("El artículo adquirido con id: " + idArticuloA + " no existe.");
        }
        return articulosAdquiridosDAO.findById(idArticuloA).get();
    }

    @Override
    public List<ArticulosAdquiridos> findAll() throws Exception {
        List<ArticulosAdquiridos> articulosAdquiridos = articulosAdquiridosDAO.findAll();
        if(articulosAdquiridos.isEmpty()){
            throw new Exception("No hay artículos adquiridos disponibles.");
        }
        return articulosAdquiridos;
    }

    private void validacionesCrear(ArticulosAdquiridos articulosAdquiridos) throws Exception{

        if(articulosAdquiridos.getEstudiante().getIdEstudiante() == null || articulosAdquiridos.getEstudiante().getIdEstudiante()<0){
            throw new Exception("Debe ingresar un estudiante válido.");
        }
        if(!estudianteDAO.findById(articulosAdquiridos.getEstudiante().getIdEstudiante()).isPresent()){
            throw new Exception("Debe ingresar el id de un estudiante que este registrado.");
        }

        if(articulosAdquiridos.getArticulos().getIdArticulo() == null || articulosAdquiridos.getArticulos().getIdArticulo() <0){
            throw new Exception("Debe ingresar un artículo válido.");
        }
        if(!articulosDAO.findById(articulosAdquiridos.getArticulos().getIdArticulo()).isPresent()){
            throw new Exception("Debe ingresar un id Articulo que este registrado.");
        }

        if(articulosAdquiridos.getUsuarioCreador() == null
                || articulosAdquiridos.getUsuarioCreador().trim().equals("")
                || Validaciones.isStringLenght(articulosAdquiridos.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar un usuario creador del artículo válido.");
        }
        if(articulosAdquiridos.getFechaCreacion() == null || articulosAdquiridos.getFechaCreacion().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha válida.");
        }
        Date fechaActual = new Date();

        if(articulosAdquiridos.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
    private void validacionesActualizar(ArticulosAdquiridosDTO articulosAdquiridosDTO) throws Exception{
        if(articulosAdquiridosDTO.getIdArticuloAdquirido() == null){
            throw new Exception("Debe ingresar el id del artículo que desea actualizar.");
        }
        if(!articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())){
            throw new Exception("No se encontró un artículo adquirido con ese id.");
        }
        if(!articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())){
            throw new Exception("No se encontró un artículo con ese id.");
        }
        if(!estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())){
            throw new Exception("No se encontró un estudiante con ese id.");
        }
        if(articulosAdquiridosDTO.getIdArticulos().toString().equals("")){
            throw new Exception("Debe ingresar un id de artículo.");
        }
        if(articulosAdquiridosDTO.getIdEstudiante().toString().equals("")){
            throw new Exception("Debe ingresar un id de un estudiante.");
        }
        if(articulosAdquiridosDTO.getIdArticulos()<0){
            throw new Exception("Debe ingresar un id de artículo válido.");
        }
        if(articulosAdquiridosDTO.getIdEstudiante()<0){
            throw new Exception("Debe ingresar un id de estudiante válido.");
        }
        if(!articulosDAO.findById(articulosAdquiridosDTO.getIdArticulos()).isPresent()){
            throw new Exception("Debe ingresar un id de un articulo que exista.");
        }
        if(!estudianteDAO.findById(articulosAdquiridosDTO.getIdEstudiante()).isPresent()){
            throw new Exception("Debe ingresar un id de un estudiante que exista.");
        }
        Date fechaActual = new Date();
        if(articulosAdquiridosDTO.getUsuarioModificador() == null || articulosAdquiridosDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(articulosAdquiridosDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre de usuario modifcador es muy largo, solo puede contener 50 caracteres.");
        }
        if(articulosAdquiridosDTO.getFechaModificacion() == null || articulosAdquiridosDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        if(articulosAdquiridosDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
