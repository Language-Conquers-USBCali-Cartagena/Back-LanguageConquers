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
    public Rol registrar(Rol rol) throws Exception {
        if(rol.getNombre()==null || rol.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del rol");
        }
        if(Validaciones.isStringLenght(rol.getNombre(), 50)){
            throw new Exception("El nombre del rol no puede superar los 50 caracteres");
        }
        if(rol.getReto().getIdReto()<0){
            throw new Exception("Debe ingresar un Id reto mayor a 0");
        }
        if(retoDAO.findById(rol.getIdRol()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un Id Reto valido");
        }
        if(rol.getUsuarioCreador() == null || rol.getUsuarioCreador().trim().equals("")){
            throw new Exception("Se debe ingresar el usuario creador");
        }
        if(Validaciones.isStringLenght(rol.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador no debe superar los 50 caracteres");
        }
        if(rol.getFechaCreacion()==null || rol.getFechaCreacion().equals("")){
            throw new Exception("Se debe ingresar una fecha de creación");
        }
        Date fechaActual = new Date();
        if(rol.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No se puede ingresar una fecha que aun no ha sucedido");
        }
        rol.setNombre(rol.getNombre());
        rol.setReto(retoDAO.findById(rol.getReto().getIdReto()).get());
        rol.setUsuarioCreador(rol.getUsuarioCreador());
        rol.setFechaCreacion(rol.getFechaCreacion());
        return rolDAO.save(rol);

    }

    @Override
    public Rol actualizar(RolDTO rolDTO) throws Exception {
        Rol rol = null;
        if(rolDTO.getIdRol()==null || rolDTO.getIdRol().equals("")){
            throw new Exception("Debe ingresar el Id del rol que desea modificar");
        }
        if(!rolDAO.existsById(rolDTO.getIdRol())){
            throw new Exception("No existe el rol con ese Id");
        }
        if(rolDTO.getNombre()==null || rolDTO.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del rol");
        }
        if(Validaciones.isStringLenght(rolDTO.getNombre(), 50)){
            throw new Exception("El nombre del rol no puede superar los 50 caracteres");
        }
        if(rolDTO.getIdReto()<0){
            throw new Exception("Debe ingresar un Id reto mayor a 0");
        }
        if(retoDAO.findById(rolDTO.getIdRol()).toString().equals("Optional.empty")){
            throw new Exception("Debe ingresar un Id Reto valido");
        }
        if(rolDTO.getUsuarioModificador() == null || rolDTO.getUsuarioModificador().trim().equals("")){
            throw new Exception("Se debe ingresar el usuario modificador");
        }
        if(Validaciones.isStringLenght(rolDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador no debe superar los 50 caracteres");
        }
        if(rolDTO.getFechaModificacion()==null || rolDTO.getFechaModificacion().equals("")){
            throw new Exception("Se debe ingresar una fecha de modificación");
        }
        Date fechaActual = new Date();
        if(rolDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No se puede ingresar una fecha que aun no ha sucedido");
        }
        rol = rolDAO.findById(rolDTO.getIdRol()).get();
        rol.setNombre(rolDTO.getNombre());
        rol.setReto(retoDAO.findById(rolDTO.getIdReto()).get());
        rol.setUsuarioCreador(rolDTO.getUsuarioCreador());
        rol.setFechaCreacion(rolDTO.getFechaCreacion());
        return rolDAO.save(rol);
    }

    @Override
    public void eliminar(Long idRol) throws Exception {
        if(idRol == null){
            throw new Exception("El id del rol es obligatorio");
        }
        if(rolDAO.existsById(idRol)==false){
            throw new Exception("No se encontro el avatar con ese id");
        }
        if(!retoEstudianteDAO.findByIdRol(idRol).isEmpty()){
            throw new Exception("No se puede eliminar el rol porque esta asignado en un reto estudiante");
        }
        rolDAO.deleteById(idRol);
    }

    @Override
    public List<Rol> listar() throws Exception {
        return rolDAO.findAll();
    }
}
