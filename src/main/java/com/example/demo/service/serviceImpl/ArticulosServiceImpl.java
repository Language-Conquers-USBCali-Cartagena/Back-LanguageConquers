package com.example.demo.service.serviceImpl;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.model.Articulos;
import com.example.demo.model.Categoria;
import com.example.demo.model.Estado;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.service.ArticulosService;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.EstadoService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class ArticulosServiceImpl implements ArticulosService {
    @Autowired
    ArticulosDAO articulosDAO;

    @Autowired
    EstadoDAO estadoDAO;

    @Autowired
    CategoriaDAO categoriaDAO;

    @Autowired
    ArticulosAdquiridosDAO articulosAdquiridosDAO;

    @Override
    public String registrar(Articulos articulos) throws Exception {
        validacionesCrear(articulos);
        articulosDAO.save(articulos);
        return "Se creo correctamente el artículo.";
    }

    @Override
    public String actualizar(ArticulosDTO articulosDTO) throws Exception {
        Articulos articulo = null;
        validacionesActualizar(articulosDTO);
        articulo = articulosDAO.findById(articulosDTO.getIdArticulo()).orElse(null);
        articulo.setNombre(articulosDTO.getNombre());
        articulo.setDescripcion(articulosDTO.getDescripcion());
        articulo.setPrecio(articulosDTO.getPrecio());
        articulo.setNivelValido(articulosDTO.getNivelValido());
        articulo.setImagen(articulosDTO.getImagen());
        articulo.setEstado(estadoDAO.findById(articulosDTO.getIdEstado()).orElse(null));
        articulo.setCategoria(categoriaDAO.findById(articulosDTO.getIdCategoria()).orElse(null));
        articulo.setUsuarioModificador(articulosDTO.getUsuarioModificador());
        articulo.setFechaModificacion(articulosDTO.getFechaModificacion());
        articulosDAO.save(articulo);
        return "Se actualizo correctamente el artículo.";
    }

    @Override
    public String eliminar(Long idArticulo) throws Exception {
        if(idArticulo == null){
            throw new Exception("Se debe ingresar el id del artículo.");
        }
        if(!articulosDAO.existsById(idArticulo)){
            throw new Exception("El artículo con id: " + idArticulo + " no existe.");
        }
        if(!articulosAdquiridosDAO.findByIdArticulo(idArticulo).isEmpty()){
            throw new Exception("No se puede eliminar el artículo porque se encuentra asociado a un articulo adquirido de un estudiante.");
        }
        articulosDAO.deleteById(idArticulo);
        return "El artículo se ha eliminado exitosamente.";
    }

    @Override
    public Articulos findById(Long idArticulo) throws Exception {
        if(idArticulo == null){
            throw new Exception("Se debe ingresar el id del artículo.");
        }
        if(!articulosDAO.existsById(idArticulo)){
            throw new Exception("El articulo con id: " + idArticulo + " no existe.");
        }
        return articulosDAO.findById(idArticulo).get();
    }

    @Override
    public List<Articulos> findAll() throws Exception {
        List<Articulos> articulos = articulosDAO.findAll();
        if(articulos.isEmpty()){
            throw new Exception("No hay artículos disponibles.");
        }
        return articulos;
    }

    private void validacionesCrear (Articulos articulos) throws Exception{

        if(articulos.getNombre().equals(null) || articulos.getNombre().trim().equals("") ||
                Validaciones.isStringLenght(articulos.getNombre(), 50)){
            throw new Exception("Debe ingresar un nombre para el artículo.");
        }
        if(articulos.getPrecio()<0){
            throw new Exception("Se debe ingresar el precio del artículo.");
        }
        if(articulos.getDescripcion().equals(null)|| articulos.getDescripcion().trim().equals("") ||
                Validaciones.isStringLenght(articulos.getDescripcion(), 200)){
            throw new Exception("Debe ingresar una descripción para el artículo.");
        }
        if(articulos.getNivelValido()<0){
            throw new Exception("Se debe ingresar el nivel válido del artículo.");
        }
        if(articulos.getImagen().equals(null) || articulos.getImagen().trim().equals("")){
            throw new Exception("Debe ingresar una imagen para el artículo.");
        }
        if(articulos.getCategoria().equals(null) || articulos.getCategoria().getIdCategoria() <0){
            throw new Exception("Debe ingresar una categoría válida.");
        }
        if(articulos.getEstado().equals(null) || articulos.getEstado().getIdEstado() <0){
            throw new Exception("Debe ingresar un estado válido.");
        }
        if(categoriaDAO.findById(articulos.getCategoria().getIdCategoria()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id categoría válido.");
        }
        if(estadoDAO.findById(articulos.getEstado().getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id estado válido.");
        }
        if(articulos.getUsuarioCreador() == null
                || articulos.getUsuarioCreador().trim().equals("")
                || Validaciones.isStringLenght(articulos.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar un usuario creador del artículo válido.");
        }
        if(articulos.getFechaCreacion() == null
                || articulos.getFechaCreacion().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha válida.");
        }
        Date fechaActual = new Date();
        if(articulos.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
    private void validacionesActualizar(ArticulosDTO articulosDTO) throws Exception{
        if(articulosDTO.getIdArticulo() == null ){
            throw new Exception("Debe ingresar el id del artículo que desea actualizar. ");
        }
        if(!articulosDAO.existsById(articulosDTO.getIdArticulo())){
            throw new Exception("No se encontró un artículo con ese id.");
        }
        if(!categoriaDAO.existsById(articulosDTO.getIdCategoria())){
            throw new Exception("No existe una categoría con ese id.");
        }
        if(!estadoDAO.existsById(articulosDTO.getIdEstado())){
            throw new Exception("No existe un estado con ese id.");
        }
        if(articulosDTO.getIdEstado().toString().equals("")){
            throw new Exception("Debe ingresar un id estado.");
        }
        if(articulosDTO.getIdCategoria().toString().equals("")){
            throw new Exception("Debe ingresar un id de categoría.");
        }
        if(articulosDTO.getIdEstado()<0){
            throw new Exception("Debe ingresar un id de estado válido.");
        }
        if(articulosDTO.getIdCategoria()<0){
            throw new Exception("Debe ingresar un id de categoría válido.");
        }
        if(estadoDAO.findById(articulosDTO.getIdEstado()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id de estado que exista.");
        }
        if(estadoDAO.findById(articulosDTO.getIdCategoria()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id de categoría que exista.");
        }
        if(articulosDTO.getNombre().equals(null) || articulosDTO.getNombre().trim().equals("") ||
                Validaciones.isStringLenght(articulosDTO.getNombre(), 50)){
            throw new Exception("Debe ingresar un nombre para el artículo.");
        }
        if(articulosDTO.getPrecio()<0){
            throw new Exception("Se debe ingresar el precio del artículo.");
        }
        if(articulosDTO.getDescripcion().equals(null)|| articulosDTO.getDescripcion().trim().equals("") ||
                Validaciones.isStringLenght(articulosDTO.getDescripcion(), 200)){
            throw new Exception("Debe ingresar una descripción para el artículo.");
        }
        if(articulosDTO.getNivelValido()<0){
            throw new Exception("Se debe ingresar el nivel válido del artículo.");
        }
        if(articulosDTO.getImagen().equals(null) || articulosDTO.getImagen().trim().equals("")){
            throw new Exception("Debe ingresar una imagen para el artículo.");
        }
        Date fechaActual = new Date();
        if(articulosDTO.getUsuarioModificador()==null || articulosDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(articulosDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.");
        }
        if(articulosDTO.getFechaModificacion()==null || articulosDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        if(articulosDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }

    }
}
