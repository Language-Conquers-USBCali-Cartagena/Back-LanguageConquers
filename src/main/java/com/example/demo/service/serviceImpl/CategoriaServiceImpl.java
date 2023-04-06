package com.example.demo.service.serviceImpl;

import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.model.Categoria;
import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.service.CategoriaService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Autowired
    private EstadoDAO estadoDAO;

    @Autowired
    private ArticulosDAO articulosDAO;

    @Override
    public String registrar(Categoria categoria) throws Exception {
        validacionesCrear(categoria);
        categoriaDAO.save(categoria);
        return "Se registro exitosamente la categoría.";
    }

    @Override
    public String actualizar(CategoriaDTO categoriaDTO) throws Exception {
        Categoria categoria = null;
        validacionesActualizar(categoriaDTO);
        categoria = categoriaDAO.findById(categoriaDTO.getIdCategoria()).orElse(null);
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        categoria.setEstado(estadoDAO.findById(categoriaDTO.getIdEstado()).orElse(null));
        categoria.setUsuarioModificador(categoriaDTO.getUsuarioModificador());
        categoria.setFechaModificacion(categoriaDTO.getFechaModificacion());
        categoriaDAO.save(categoria);
        return "Se actualizo exitosamente la categoría.";
    }

    @Override
    public String eliminar(Long idCategoria) throws Exception {
        if(idCategoria == null ){
            throw new Exception("El id de la categoría es obligatorio.");
        }
        if (!categoriaDAO.existsById(idCategoria)){
            throw new Exception("No se encontró la categoría con ese id.");
        }
        if(!articulosDAO.findByIdCategoria(idCategoria).isEmpty()){
            throw new Exception("No se puede eliminar la categoría porque esta siendo utilizada en un artículo.");
        }
        categoriaDAO.deleteById(idCategoria);
        return "Se elimino exitosamente la categoría.";
    }

    @Override
    public List<Categoria> findAll() throws Exception {
        return categoriaDAO.findAll();
    }

    @Override
    public Categoria findById(Long idCategoria) throws Exception {
        if(idCategoria == null){
            throw new Exception("Debe ingresar el id de la categoría.");
        }
        if(!categoriaDAO.existsById(idCategoria)){
            throw new Exception("La categoría con ese id no existe.");
        }
        return categoriaDAO.findById(idCategoria).get();
    }

    private void validacionesCrear(Categoria categoria) throws Exception{
        if(categoria.getNombre() == null|| categoria.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre de la categoría.");
        }
        if(Validaciones.isStringLenght(categoria.getNombre(),50 )){
            throw new Exception("El nombre de la categoría no debe contener más de 50 caracteres.");
        }
        if(categoria.getDescripcion() == null || categoria.getDescripcion().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción de la categoría.");
        }
        if(Validaciones.isStringLenght(categoria.getDescripcion(),200 )){
            throw new Exception("La descripción no debe contener más de 200 caracteres.");
        }
        if(categoria.getEstado().getIdEstado() == null){
            throw new Exception("Se debe ingresar el id del estado.");
        }
        if(categoria.getEstado().getIdEstado()<0){
            throw new Exception("Se debe ingresar un id de estado válido.");
        }
        if(!estadoDAO.existsById(categoria.getEstado().getIdEstado())){
            throw new Exception("No existe un estado con el id ingresado, ingrese un id válido.");
        }
        if(categoria.getUsuarioCreador() == null || categoria.getUsuarioCreador().trim().equals("")){
            throw new Exception("Se debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(categoria.getUsuarioCreador(),50 )){
            throw new Exception("El usuario creador no debe contener más de 50 caracteres.");
        }
        if(categoria.getFechaCreacion() == null ){
            throw new Exception("Se debe ingresar la fecha de creación.");
        }/*
        Date fechaActual = new Date();
        if(categoria.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }*/
    }

    private void validacionesActualizar(CategoriaDTO categoriaDTO) throws Exception{
        if(categoriaDTO.getIdCategoria() == null){
            throw new Exception("Debe ingresar el id de la categoría que desea actualizar.");
        }
        if(!categoriaDAO.existsById(categoriaDTO.getIdCategoria())){
            throw new Exception("No existe una categoría con ese id.");
        }
        if(categoriaDTO.getNombre() == null|| categoriaDTO.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre de la categoría.");
        }
        if(Validaciones.isStringLenght(categoriaDTO.getNombre(),50 )){
            throw new Exception("El nombre de la categoría no debe contener más de 50 caracteres.");
        }
        if(categoriaDTO.getDescripcion() == null || categoriaDTO.getDescripcion().trim().equals("")){
            throw new Exception("Se debe ingresar una descripción de la categoría.");
        }
        if(Validaciones.isStringLenght(categoriaDTO.getDescripcion(),200 )){
            throw new Exception("La descripción no debe contener más de 200 caracteres.");
        }
        if(categoriaDTO.getIdEstado() == null){
            throw new Exception("Se debe ingresar el id del estado.");
        }
        if(categoriaDTO.getIdEstado()<0){
            throw new Exception("Se debe ingresar un id de estado válido.");
        }
        if(!estadoDAO.existsById(categoriaDTO.getIdEstado())){
            throw new Exception("No existe un estado con el id ingresado, ingrese un id válido.");
        }
        if(categoriaDTO.getUsuarioModificador() == null || categoriaDTO.getUsuarioModificador().trim().equals("")){
            throw new Exception("Se debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(categoriaDTO.getUsuarioModificador(),50 )){
            throw new Exception("El usuario modificador no debe contener más de 50 caracteres.");
        }
        if(categoriaDTO.getFechaModificacion() == null || categoriaDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Se debe ingresar la fecha de modificación.");
        }
        /*
        Date fechaActual = new Date();
        if(categoriaDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }*/
    }

}
