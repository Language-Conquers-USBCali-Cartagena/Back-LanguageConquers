package com.example.demo.service.serviceImpl;

import com.example.demo.dao.RetoDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.dao.RolDAO;
import com.example.demo.model.Rol;
import com.example.demo.model.dto.RolDTO;
import com.example.demo.service.RolService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDAO rolDAO;

    @Autowired
    private RetoEstudianteDAO retoEstudianteDAO;

    @Autowired
    private RetoDAO retoDAO;
    @Override
    public String registrar(Rol rol) throws Exception {
        validacionesCrear(rol);
        rolDAO.save(rol);
        return "Se creo exitosamente el rol.";

    }
    @Override
    public String actualizar(Rol rol) throws Exception {
        validacionesActualizar(rol);
        rolDAO.save(rol);
        return "Se actualizo exitosamente el rol.";
    }

    @Override
    public String eliminar(Long idRol) throws Exception {
        if(idRol == null){
            throw new Exception("El id del rol es obligatorio.");
        }
        if(!rolDAO.existsById(idRol)){
            throw new Exception("No se encontró el rol con ese id.");
        }
        if(!retoEstudianteDAO.findByIdRol(idRol).isEmpty()){
            throw new Exception("No se puede eliminar el rol porque esta asignado en un reto estudiante.");
        }
        rolDAO.deleteById(idRol);
        return "Se elimino exitosamente el rol.";
    }

    @Override
    public List<Rol> listar() throws Exception {
        return rolDAO.findAll();
    }

    private void validacionesCrear(Rol rol)throws Exception{
        if(rol.getNombre()==null || rol.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del rol.");
        }
        if(Validaciones.isStringLenght(rol.getNombre(), 50)){
            throw new Exception("El nombre del rol no puede superar los 50 caracteres.");
        }
        if(rol.getReto().getIdReto()<0){
            throw new Exception("Debe ingresar un id reto válido.");
        }
        if(retoDAO.findById(rol.getReto().getIdReto()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id reto válido.");
        }
        if(rol.getUsuarioCreador() == null || rol.getUsuarioCreador().trim().equals("")){
            throw new Exception("Se debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(rol.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador no debe superar los 50 caracteres.");
        }
        if(rol.getFechaCreacion()==null){
            throw new Exception("Se debe ingresar una fecha de creación.");
        }
        Date fechaActual = new Date();
        if(rol.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No se puede ingresar una fecha que aun no ha sucedido.");
        }
    }
    private void validacionesActualizar(Rol rol)throws Exception{
        if(rol.getIdRol() == null){
            throw new Exception("Debe ingresar el id del rol que desea modificar.");
        }
        if(!rolDAO.existsById(rol.getIdRol())){
            throw new Exception("No existe el rol con ese id.");
        }
        if(rol.getNombre()==null || rol.getNombre().equals("")){
            throw new Exception("Se debe ingresar el nombre del rol.");
        }
        if(Validaciones.isStringLenght(rol.getNombre(), 50)){
            throw new Exception("El nombre del rol no puede superar los 50 caracteres.");
        }
        if(rol.getReto().getIdReto()<0){
            throw new Exception("Debe ingresar un id reto válido.");
        }
        if(retoDAO.findById(rol.getReto().getIdReto()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un id reto válido.");
        }
        if(rol.getUsuarioModificador() == null || rol.getUsuarioModificador().trim().equals("")){
            throw new Exception("Se debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(rol.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador no debe superar los 50 caracteres.");
        }
        if(rol.getFechaModificacion()==null){
            throw new Exception("Se debe ingresar una fecha de modificación.");
        }
        Date fechaActual = new Date();
        if(rol.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No se puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
