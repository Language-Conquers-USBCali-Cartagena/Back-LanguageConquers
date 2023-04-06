package com.example.demo.service.serviceImpl;

import com.example.demo.dao.BitacoraDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.model.Bitacora;
import com.example.demo.model.dto.BitacoraDTO;
import com.example.demo.service.BitacoraService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class BitacoraServiceImpl implements BitacoraService {

    @Autowired
    private BitacoraDAO bitacoraDAO;

    @Autowired
    private EstudianteDAO estudianteDAO;

    @Autowired
    private ProfesorDAO profesorDAO;

    @Override
    public String registrar(Bitacora bitacora) throws Exception {
        validacionesCrear(bitacora);
        bitacoraDAO.save(bitacora);
        return "Se creo correctamente el registro en la bitácora.";
    }

    @Override
    public String actualizar(BitacoraDTO bitacoraDTO) throws Exception {
        Bitacora bitacora = null;
        validacionesActualizar(bitacoraDTO);
        bitacora = bitacoraDAO.findById(bitacoraDTO.getIdBitacora()).orElse(null);
        bitacora.setFechaIngreso(bitacoraDTO.getFechaIngreso());
        bitacora.setFechaSalida(bitacoraDTO.getFechaSalida());
        bitacora.setIdUsuario(bitacoraDTO.getIdUsuario());
        bitacora.setUsuarioModificador(bitacoraDTO.getUsuarioModificador());
        bitacora.setFechaModificacion(bitacoraDTO.getFechaModificacion());
        bitacoraDAO.save(bitacora);
        return "Se actualizo el registro en la bitácora.";
    }

    @Override
    public String eliminar(Long idBitacora) throws Exception {
        if(idBitacora == null){
            throw new Exception("El id del registro en la bitácora es obligatorio.");
        }
        if(!bitacoraDAO.existsById(idBitacora)){
            throw new Exception("No se encontró ningún registro en la bitácora con ese id.");
        }
        bitacoraDAO.deleteById(idBitacora);
        return "Se elimino exitosamente la bitácora.";
    }

    @Override
    public List<Bitacora> listar() {
        return bitacoraDAO.findAll();
    }
    private void validacionesCrear(Bitacora bitacora) throws Exception{
        if(bitacora.getFechaIngreso() == null ){
            throw new Exception("Se debe ingresar una fecha de ingreso válida.");
        }
        if(bitacora.getFechaSalida() == null ){
            throw new Exception("Se debe ingresar una fecha de salida válida.");
        }
        if(bitacora.getIdUsuario() == null){
            throw new Exception("Debe ingresar el id de un profesor o estudiante.");
        }
        if(!estudianteDAO.existsById(bitacora.getIdUsuario())
                && !profesorDAO.existsById(bitacora.getIdUsuario())){
            throw new Exception("Debe ingresar un id válido de un  estudiante o de un profesor.");
        }
        if(bitacora.getUsuarioCreador() == null || bitacora.getUsuarioCreador().trim().equals("")){
            throw new Exception("Debe ingresar un usuario creador.");
        }
        if(Validaciones.isStringLenght(bitacora.getUsuarioCreador(),50)){
            throw new Exception("Debe ingresar un usuario creador que no supere los 50 caracteres.");
        }
        if(bitacora.getFechaCreacion() == null){
            throw new Exception("Se debe ingresar una fecha de creación válida.");
        }

        Date fechaActual = new Date();
        if(bitacora.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
    private void validacionesActualizar(BitacoraDTO bitacoraDTO)throws Exception{
        if(bitacoraDTO.getIdBitacora() == null){
            throw new Exception("Debe ingresar el id del registro que desea modificar.");
        }
        if(!bitacoraDAO.existsById(bitacoraDTO.getIdBitacora())){
            throw new Exception("No existe un registro en la bitácora con ese id.");
        }
        if(bitacoraDTO.getFechaIngreso() == null){
            throw new Exception("Se debe ingresar una fecha de ingreso válida.");
        }
        if(bitacoraDTO.getFechaSalida() == null){
            throw new Exception("Se debe ingresar una fecha de salida válida.");
        }
        if(bitacoraDTO.getIdUsuario() == null){
            throw new Exception("Debe ingresar el id de un profesor o estudiante.");
        }
        if(bitacoraDTO.getUsuarioModificador() == null || bitacoraDTO.getUsuarioModificador().trim().equals("")){
            throw new Exception("Debe ingresar un usuario modificador.");
        }
        if(Validaciones.isStringLenght(bitacoraDTO.getUsuarioModificador(),50)){
            throw new Exception("Debe ingresar un usuario modificador que no supere los 50 caracteres.");
        }
        if(bitacoraDTO.getFechaModificacion() == null){
            throw new Exception("Se debe ingresar una fecha de modificación válida.");
        }
        Date fechaActual = new Date();
        if(bitacoraDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
