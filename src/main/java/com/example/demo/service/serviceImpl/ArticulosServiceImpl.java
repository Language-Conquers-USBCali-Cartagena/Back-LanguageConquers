package com.example.demo.service.serviceImpl;

import com.example.demo.dao.ArticulosDAO;
import com.example.demo.model.Articulos;
import com.example.demo.model.Categoria;
import com.example.demo.model.Estado;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.service.ArticulosService;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class ArticulosServiceImpl implements ArticulosService {
    @Autowired
    ArticulosDAO articulosDAO;

    @Autowired
    EstadoService estadoService;

    @Autowired
    CategoriaService categoriaService;

    @Override
    public String registrar(Articulos articulos) throws Exception {
        validacionesCrear(articulos);
        articulosDAO.save(articulos);
        return "Se creo correctamente el artículo.";

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
        idEstado = estadoService.findById(articulosDTO.getIdEstado());
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
        if(articulos.getCategoria().equals(null)){
            throw new Exception("Debe ingresar una categoría.");
        }
        if(articulos.getEstado().equals(null)){
            throw new Exception("Debe ingresar un estado.");
        }
        if(articulos.getNombre().equals(null)){
            throw new Exception("Debe ingresar un nombre para el artículo.");
        }
        if(articulos.getDescripcion().equals(null)){
            throw new Exception("Debe ingresar una descripción para el artículo.");
        }
        if(articulos.getImagen().equals(null)){
            throw new Exception("Debe ingresar una imagen para el artículo.");
        }
    }
}
