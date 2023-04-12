package com.example.demo.service.serviceImpl;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.model.Articulos;
import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;
import com.example.demo.service.ArticulosAdquiridosService;
import com.example.demo.service.EstudianteService;
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

    @Autowired
    EstudianteService estudianteService;

    @Override
    public String registrar(ArticulosAdquiridos articulosAdquiridos) throws Exception {
        validacionesCrear(articulosAdquiridos);
        articulosAdquiridosDAO.save(articulosAdquiridos);
        return "Se creo correctamente el artículo adquirido.";
    }

    @Override
    public String actualizar(ArticulosAdquiridos articulosAdquiridos) throws Exception {
        validacionesActualizar(articulosAdquiridos);
        articulosAdquiridosDAO.save(articulosAdquiridos);
        return "Se actualizo correctamente el artículo adquirido.";
    }

    @Override
    public String eliminar(Long idArticuloA) throws Exception {
        if(idArticuloA == null){
            throw new Exception("Se debe ingresar el id del artículo adquirido.");
        }
        if(!articulosAdquiridosDAO.existsById(idArticuloA)){
            throw new Exception("El artrículo adquirido con ese id no existe.");
        }
        articulosAdquiridosDAO.deleteById(idArticuloA);
        return "El artículo adquirido se ha eliminado exitosamente.";
    }

    @Override
    public String eliminarPorIds(Long idEstudiante, Long idArticulo) throws Exception {
        if(!estudianteDAO.existsById(idEstudiante)){
            throw new Exception("El estudiante con ese id no existe.");
        }
        if(!articulosDAO.existsById(idArticulo)){
            throw new Exception("El articulo con ese id no existe.");
        }
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosDAO.findByIdArticuloAndIdEstudiante(idEstudiante, idArticulo);
        if(articulosAdquiridos == null){
            throw new Exception("No se encontró ningún registro correspondiente en la base de datos.");
        }
        articulosAdquiridosDAO.deleteById(articulosAdquiridos.getIdArticuloAdquirido());
        return "Se elimino el articulo correctamente";
    }

    @Override
    public ArticulosAdquiridos findById(Long idArticuloA) throws Exception {
        if(idArticuloA == null){
            throw new Exception("Se debe ingresar el id del artículo adquirido.");
        }
        if(!articulosAdquiridosDAO.existsById(idArticuloA)){
            throw new Exception("El artículo adquirido con ese id no existe.");
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

    @Override
    public Integer comprar(Long idEstudiante, Long idArticulo) throws Exception {
        Estudiante estudiante = estudianteDAO.findById(idEstudiante).get();
        Articulos articulo = articulosDAO.findById(idArticulo).get();

        if(!articulosDAO.existsById(idArticulo)){
            throw new Exception("El articulo con ese id no existe.");
        }
        if(!estudianteDAO.existsById(idEstudiante)){
            throw new Exception("El estudiante con ese id no existe.");
        }
        Double monedasEstudiante = Double.valueOf(estudiante.getMonedasObtenidas());
        Double precio = articulo.getPrecio();

        if(monedasEstudiante < precio){
            throw new Exception("No tienes suficientes monedas para comprar este articulo.");
        }
        monedasEstudiante = monedasEstudiante - precio;
        ArticulosAdquiridos articulosAdquiridos = new ArticulosAdquiridos();
        articulosAdquiridos.setArticulos(articulo);
        articulosAdquiridos.setEstudiante(estudiante);
        articulosAdquiridos.setFechaCreacion(new Date());
        articulosAdquiridos.setUsuarioCreador(estudiante.getNombre());
        estudiante.setMonedasObtenidas(monedasEstudiante.intValue());
        estudiante.setUsuarioModificador("admin");
        estudianteService.actualizar(estudiante);
        registrar(articulosAdquiridos);

        return monedasEstudiante.intValue();
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
        if(articulosAdquiridos.getFechaCreacion() == null){
            throw new Exception("Se debe ingresar una fecha válida.");
        }
        Date fechaActual = new Date();
        if(articulosAdquiridos.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
    private void validacionesActualizar(ArticulosAdquiridos articulosAdquiridos) throws Exception{
        if(articulosAdquiridos.getIdArticuloAdquirido() == null){
            throw new Exception("Debe ingresar el id del artículo que desea actualizar.");
        }
        if(articulosAdquiridos.getIdArticuloAdquirido()<0){
            throw new Exception("Debe ingresar el id del artículo adquirido válido.");
        }
        if(!articulosAdquiridosDAO.existsById(articulosAdquiridos.getIdArticuloAdquirido())){
            throw new Exception("No se encontró un artículo adquirido con ese id.");
        }
        if(articulosAdquiridos.getArticulos().getIdArticulo() == null){
            throw new Exception("Debe ingresar un id de artículo.");
        }
        if(articulosAdquiridos.getEstudiante().getIdEstudiante() == null){
            throw new Exception("Debe ingresar un id de un estudiante.");
        }
        if(articulosAdquiridos.getArticulos().getIdArticulo()<0){
            throw new Exception("Debe ingresar un id de artículo válido.");
        }
        if(articulosAdquiridos.getEstudiante().getIdEstudiante()<0){
            throw new Exception("Debe ingresar un id de estudiante válido.");
        }
        if(!articulosDAO.existsById(articulosAdquiridos.getArticulos().getIdArticulo())){
            throw new Exception("No se encontró un artículo con ese id.");
        }
        if(!estudianteDAO.existsById(articulosAdquiridos.getEstudiante().getIdEstudiante())){
            throw new Exception("No se encontró un estudiante con ese id.");
        }
        Date fechaActual = new Date();
        if(articulosAdquiridos.getUsuarioModificador() == null || articulosAdquiridos.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(articulosAdquiridos.getUsuarioModificador(),50)){
            throw new Exception("El nombre de usuario modifcador es muy largo, solo puede contener 50 caracteres.");
        }
        if(articulosAdquiridos.getFechaModificacion() == null){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        if(articulosAdquiridos.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
