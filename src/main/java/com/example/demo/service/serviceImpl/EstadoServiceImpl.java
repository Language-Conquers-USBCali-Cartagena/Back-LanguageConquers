package com.example.demo.service.serviceImpl;


import com.example.demo.dao.*;
import com.example.demo.model.Estado;
import com.example.demo.model.dto.EstadoDTO;
import com.example.demo.service.EstadoService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoDAO estadoDAO;
    @Autowired
    private CategoriaDAO categoriaDAO;
    @Autowired
    private MisionEstudianteDAO misionEstudianteDAO;
    @Autowired
    private RetoDAO retoDAO;
    @Autowired
    private CursoDAO cursoDAO;
    @Autowired
    private EstudianteDAO estudianteDAO;
    @Autowired
    private RetoEstudianteDAO retoEstudianteDAO;
    @Autowired
    private ArticulosDAO articulosDAO;
    @Override
    public String registrar(Estado estado) throws Exception {
        validacionesCrear(estado);
        estadoDAO.save(estado);
        return "Se registro el estado satisfactoriamente";
    }

    @Override
    public String actualizar(EstadoDTO estadoDTO) throws Exception {
        Estado estado = null;
        validacionesActualizar(estadoDTO);
        estado = estadoDAO.findById(estadoDTO.getIdEstado()).orElse(null);
        estado.setEstado(estadoDTO.getEstado());
        estado.setUsuarioModificador(estadoDTO.getUsuarioModificador());
        estado.setFechaModificacion(estadoDTO.getFechaModificacion());
        estadoDAO.save(estado);
        return "Se actualizo el estado";
    }

    @Override
    public void eliminar(Long idEstado) throws Exception {
        if(idEstado == null){
            throw new Exception("El Id del estado es obligatorio");
        }
        if(!estadoDAO.existsById(idEstado)){
            throw new Exception("No se encontro el estado con ese Id");
        }
        if(!categoriaDAO.findByIdEstado(idEstado).isEmpty()){
            throw new Exception("No se puede eliminar el estado porque esta siendo utilizado en una categoria");
        }
        if(!misionEstudianteDAO.findByIdEstado(idEstado).isEmpty()){
            throw new Exception("No se puede eliminar el estado porque esta siendo utilizado en mision estudiante");
        }
        if(!retoDAO.findByIdEstado(idEstado).isEmpty()){
            throw new Exception("No se puede eliminar el estado porque esta siendo utilizado en un reto");
        }
        if(!cursoDAO.findByIdEstado(idEstado).isEmpty()){
            throw new Exception("No se puede eliminar el estado porque esta siendo utilizado en un curso");
        }
        if(!estudianteDAO.findByIdEstado(idEstado).isEmpty()){
            throw new Exception("No se puede eliminar el estado porque esta siendo utilizado en un estudiante");
        }
        if(!retoEstudianteDAO.findByIdEstado(idEstado).isEmpty()){
            throw new Exception("No se puede eliminar el estado porque esta siendo utilizado en un reto estudiante");
        }
        if(!articulosDAO.findByIdEstado(idEstado).isEmpty()){
            throw new Exception("No se puede eliminar el estado porque esta siendo utilizado en un articulo");
        }
        estadoDAO.deleteById(idEstado);
    }

    @Override
    public List<Estado> listar() {
        return estadoDAO.findAll();
    }

    private void validacionesCrear(Estado estado)throws Exception{
        if(estado.getEstado() == null || estado.getEstado().trim().equals("")){
            throw new Exception("Se debe ingresar un estado");
        }
        if(Validaciones.isStringLenght(estado.getEstado(),50)){
            throw new Exception("Se debe ingresar un estado valido, no debe superar los 50 caracteres");
        }
        if(estado.getUsuarioCreador() == null || estado.getUsuarioCreador().trim().equals("")
                || Validaciones.isStringLenght(estado.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar un usuario creador");
        }
        if(estado.getFechaCreacion() == null || estado.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación");
        }
        Date fechaActual = new Date();
        if(estado.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
    }
    private void validacionesActualizar(EstadoDTO estadoDTO)throws Exception{
        if(estadoDTO.getIdEstado() == null){
            throw new Exception("Debe ingresar el Id del estado que desea actualizar");
        }
        if(!estadoDAO.existsById(estadoDTO.getIdEstado())){
            throw new Exception("No existe el estado con ese Id");
        }
        if(estadoDTO.getEstado() == null || estadoDTO.getEstado().trim().equals("")){
            throw new Exception("Se debe ingresar un estado");
        }
        if(Validaciones.isStringLenght(estadoDTO.getEstado(),50)){
            throw new Exception("Se debe ingresar un estado valido, no debe superar los 50 caracteres");
        }
        if(estadoDTO.getUsuarioModificador() == null || estadoDTO.getUsuarioModificador().trim().equals("")
                || Validaciones.isStringLenght(estadoDTO.getUsuarioModificador(),50)){
            throw new Exception("Se debe ingresar un usuario creador");
        }
        if(estadoDTO.getFechaModificacion() == null || estadoDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación");
        }
        Date fechaActual = new Date();
        if(estadoDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
    }
}
