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
    public List<Articulos> articulosObtenidos(Long idEstudiante) throws Exception {
        return articulosDAO.articulosObtenidos(idEstudiante);
    }

    @Override
    public List<Articulos> articulosNoObtenidos(Long idEstudiante) throws Exception {
        return articulosDAO.articulosNoObtenidos(idEstudiante);
    }

    @Override
    public String registrar(Articulos articulos) throws Exception {
        validacionesCrear(articulos);
        articulosDAO.save(articulos);
        return "Se creo correctamente el artículo.";
    }

    @Override
    public String actualizar(Articulos articulos) throws Exception {
        Articulos articulo = null;
        validacionesActualizar(articulos);
        articulosDAO.save(articulo);
        return "Se actualizo correctamente el artículo.";
    }

    @Override
    public String eliminar(Long idArticulo) throws Exception {
        if(idArticulo == null){
            throw new Exception("Se debe ingresar el id del artículo.");
        }
        if(!articulosDAO.existsById(idArticulo)){
            throw new Exception("El artículo con ese id no existe.");
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
            throw new Exception("El articulo con ese id no existe.");
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

    @Override
    public int articulosRegistrados() throws Exception {
        int cantidadArticulos = articulosDAO.articulosRegistrados();
        return cantidadArticulos;
    }

    private void validacionesCrear (Articulos articulos) throws Exception{

        if(articulos.getNombre()==null || articulos.getNombre().trim().equals("") ||
                Validaciones.isStringLenght(articulos.getNombre(), 50)){
            throw new Exception("Debe ingresar un nombre para el artículo.");
        }
        if(articulos.getPrecio()<0){
            throw new Exception("Se debe ingresar el precio del artículo.");
        }
        if(articulos.getDescripcion() == null|| articulos.getDescripcion().trim().equals("") ||
                Validaciones.isStringLenght(articulos.getDescripcion(), 200)){
            throw new Exception("Debe ingresar una descripción para el artículo.");
        }
        if(articulos.getNivelValido()<=0){
            throw new Exception("Se debe ingresar el nivel válido del artículo.");
        }
        if(articulos.getImagen() == null || articulos.getImagen().trim().equals("")){
            throw new Exception("Debe ingresar una imagen para el artículo.");
        }
        if(Validaciones.isStringLenght(articulos.getImagen(),250)){
            throw new Exception("La url de la imagen es muy larga, máximo 250 caracteres.");
        }
        if(articulos.getCategoria().getIdCategoria() == null || articulos.getCategoria().getIdCategoria() <=0){
            throw new Exception("Debe ingresar un idCategoría válido.");
        }
        if(!categoriaDAO.findById(articulos.getCategoria().getIdCategoria()).isPresent()){
            throw new Exception("Debe ingresar un idCategoría que este registrada.");
        }
        if(articulos.getEstado().getIdEstado() ==null || articulos.getEstado() ==null || articulos.getEstado().getIdEstado() <=0){
            throw new Exception("Debe ingresar un idEstado válido.");
        }
        if(!estadoDAO.findById(articulos.getEstado().getIdEstado()).isPresent()){
            throw new Exception("Debe ingresar un idEstado que este registrado.");
        }
        if(articulos.getUsuarioCreador() == null
                || articulos.getUsuarioCreador().trim().equals("")
                || Validaciones.isStringLenght(articulos.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar un usuario creador del artículo válido.");
        }
        if(articulos.getFechaCreacion() == null){
            throw new Exception("Se debe ingresar una fecha válida.");
        }
        Date fechaActual = new Date();
        if(articulos.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
    private void validacionesActualizar(Articulos articulos) throws Exception{
        if(articulos.getIdArticulo() == null ){
            throw new Exception("Debe ingresar el id del artículo que desea actualizar.");
        }
        if(!articulosDAO.existsById(articulos.getIdArticulo())){
            throw new Exception("No se encontró un artículo con ese id.");
        }
        if(articulos.getNombre()== null || articulos.getNombre().trim().equals("") ||
                Validaciones.isStringLenght(articulos.getNombre(), 50)){
            throw new Exception("Debe ingresar un nombre para el artículo.");
        }
        if(articulos.getDescripcion() == null|| articulos.getDescripcion().trim().equals("") ||
                Validaciones.isStringLenght(articulos.getDescripcion(), 200)){
            throw new Exception("Debe ingresar una descripción para el artículo.");
        }
        if(articulos.getImagen()==null || articulos.getImagen().trim().equals("")){
            throw new Exception("Debe ingresar una imagen para el artículo.");
        }
        if(Validaciones.isStringLenght(articulos.getImagen(),250)){
            throw new Exception("La url de la imagen es muy larga, máximo 250 caracteres.");
        }
        if(articulos.getPrecio()<0){
            throw new Exception("Se debe ingresar el precio del artículo.");
        }
        if(articulos.getCategoria().getIdCategoria() == null){
            throw new Exception("Debe ingresar un id de categoría.");
        }
        if(articulos.getCategoria().getIdCategoria()<0){
            throw new Exception("Debe ingresar un id de categoría válido.");
        }
        if(!categoriaDAO.existsById(articulos.getCategoria().getIdCategoria())){
            throw new Exception("No existe una categoría con ese id.");
        }
        if(articulos.getNivelValido()<0){
            throw new Exception("Se debe ingresar el nivel válido del artículo.");
        }
        if(articulos.getEstado().getIdEstado() == null){
            throw new Exception("Debe ingresar un id estado.");
        }
        if(articulos.getEstado().getIdEstado()<0){
            throw new Exception("Debe ingresar un id de estado válido.");
        }
        if(!estadoDAO.existsById(articulos.getEstado().getIdEstado())){
            throw new Exception("No existe un estado con ese id.");
        }
        Date fechaActual = new Date();
        if(articulos.getUsuarioModificador()==null || articulos.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(articulos.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.");
        }
        if(articulos.getFechaModificacion()==null){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        if(articulos.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }

    }
}
