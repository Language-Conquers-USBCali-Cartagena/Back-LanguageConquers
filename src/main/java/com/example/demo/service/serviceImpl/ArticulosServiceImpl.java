package com.example.demo.service.serviceImpl;

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

    @Override
    public String registrar(Articulos articulos) throws Exception {
        try{
            validacionesCrear(articulos);
            articulosDAO.save(articulos);
            return "Se creo correctamente el artículo.";
        }catch (Exception e){
            return e.getMessage();
        }


    }

    @Override
    public String actualizar(ArticulosDTO articulosDTO) throws Exception {
        Articulos articulo = null;
        Estado idEstado = null;
        Categoria idCategoria = null;
        //Todo: Faltan validaciones
        articulo = articulosDAO.findById(articulosDTO.getIdArticulo()).orElse(null);
        articulo.setPrecio(articulosDTO.getPrecio());
        articulo.setImagen(articulosDTO.getImagen());
        articulo.setNivelValido(articulosDTO.getNivelValido());
        articulo.setDescripcion(articulosDTO.getDescripcion());
        idEstado = estadoDAO.findById(articulosDTO.getIdEstado()).orElse(null);
        articulo.setEstado(idEstado);
        //idCategoria = categoriaService.

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
        //TODO: Falta validacion de si existe en articulo_adquirido
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
}
