package com.example.demo.service.serviceImpl;

import com.example.demo.dao.ArticulosDAO;
import com.example.demo.model.Articulos;
import com.example.demo.service.ArticulosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class ArticulosServiceImpl implements ArticulosService {
    @Autowired
    ArticulosDAO articulosDAO;

    @Override
    public String save(Articulos articulos) throws Exception {
        validaciones(articulos);
        articulosDAO.save(articulos);
        return "Se creo correctamente el articulo";

    }

    @Override
    public String update(Articulos articulos) throws Exception {
        validaciones(articulos);
        articulosDAO.save(articulos);
        return "Se actualizo correctamente el articulo";
    }

    @Override
    public String delete(Long idArticulo) throws Exception {
        if(!articulosDAO.existsById(idArticulo)){
            throw new Exception("El articulo no existe");
        }
        articulosDAO.deleteById(idArticulo);
        return "Se elimino correctamente el articulo";
    }

    @Override
    public List<Articulos> findAll() throws Exception {
        List<Articulos> articulos = articulosDAO.findAll();
        if(articulos.isEmpty()){
            throw new Exception("No hay articulos disponibles");
        }
        return articulos;
    }

    private void validaciones (Articulos articulos) throws Exception{
        if(articulos.getCategoria().equals(null)){
            throw new Exception("Debe ingresar una categoria");
        }
        if(articulos.getEstado().equals(null)){
            throw new Exception("Debe ingresar un estado");
        }
        if(articulos.getNombre().equals(null)){
            throw new Exception("Debe ingresar un nombre para el articulo");
        }
        if(articulos.getDescripcion().equals(null)){
            throw new Exception("Debe ingresar una descripcion para el articulo");
        }
        if(articulos.getImagen().equals(null)){
            throw new Exception("Debe ingresar una imagen para el articulo");
        }
    }
}
