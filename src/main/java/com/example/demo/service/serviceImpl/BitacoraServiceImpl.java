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
        if(bitacora.getFechaIngreso() == null || bitacora.getFechaIngreso().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha de ingreso valida");
        }
        if(bitacora.getFechaSalida() == null || bitacora.getFechaSalida().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha de salida valida");
        }
        if(bitacora.getIdUsuario() == null){
            throw new Exception("Debe ingresar el Id de un profesor o estudiante");
        }
        if(!estudianteDAO.existsById(bitacora.getIdUsuario())
                && !profesorDAO.existsById(bitacora.getIdUsuario())){
            throw new Exception("Debe ingresar un Id valido de un  estudiante o de un profesor");
        }
        if(bitacora.getUsuarioCreador() == null || bitacora.getUsuarioCreador().trim().equals("")){
            throw new Exception("Debe ingresar un usuario creador");
        }
        if(Validaciones.isStringLenght(bitacora.getUsuarioCreador(),50)){
            throw new Exception("Debe ingresar un usuario creador que no supere los 50 caracteres");
        }
        if(bitacora.getFechaCreacion() == null || bitacora.getFechaCreacion().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha de creacion valida");
        }
        Date fechaActual = new Date();
        if(bitacora.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
        bitacoraDAO.save(bitacora);
        return "Se realizo el registro de la bitacora";
    }

    @Override
    public String actualizar(BitacoraDTO bitacoraDTO) throws Exception {
        Bitacora bitacora = null;
        if(bitacoraDTO.getIdBitacora() == null){
            throw new Exception("Debe ingresar el Id del registro que desea modificar");
        }
        if(!bitacoraDAO.existsById(bitacoraDTO.getIdBitacora())){
            throw new Exception("No existe un registro en la bitacora con ese Id");
        }
        if(bitacoraDTO.getFechaIngreso() == null || bitacoraDTO.getFechaIngreso().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha de ingreso valida");
        }
        if(bitacoraDTO.getFechaSalida() == null || bitacoraDTO.getFechaSalida().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha de salida valida");
        }
        if(bitacoraDTO.getIdUsuario() == null){
            throw new Exception("Debe ingresar el Id de un profesor o estudiante");
        }
        if(bitacoraDTO.getUsuarioModificador() == null || bitacoraDTO.getUsuarioModificador().trim().equals("")){
            throw new Exception("Debe ingresar un usuario modificador");
        }
        if(Validaciones.isStringLenght(bitacoraDTO.getUsuarioModificador(),50)){
            throw new Exception("Debe ingresar un usuario modificador que no supere los 50 caracteres");
        }
        if(bitacoraDTO.getFechaModificacion() == null || bitacoraDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha de modificacion valida");
        }
        Date fechaActual = new Date();
        if(bitacoraDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
        bitacora = bitacoraDAO.findById(bitacoraDTO.getIdBitacora()).orElse(null);
        bitacora.setFechaIngreso(bitacoraDTO.getFechaIngreso());
        bitacora.setFechaSalida(bitacoraDTO.getFechaSalida());
        bitacora.setIdUsuario(bitacoraDTO.getIdUsuario());
        bitacora.setUsuarioModificador(bitacoraDTO.getUsuarioModificador());
        bitacora.setFechaModificacion(bitacoraDTO.getFechaModificacion());
        bitacoraDAO.save(bitacora);
        return "Se actualizo la Bitacora";
    }

    @Override
    public void eliminar(Long idBitacora) throws Exception {
        if(idBitacora == null){
            throw new Exception("El Id del registro en la bitacora es obligatorio");
        }
        if(!bitacoraDAO.existsById(idBitacora)){
            throw new Exception("No se encontro ningun registro en la bitacora con ese Id");
        }
        bitacoraDAO.deleteById(idBitacora);
    }

    @Override
    public List<Bitacora> listar() {
        return bitacoraDAO.findAll();
    }
}
