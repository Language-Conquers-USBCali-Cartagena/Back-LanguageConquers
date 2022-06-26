package com.example.demo.service.serviceImpl;

import com.example.demo.dao.CredencialesDAO;
import com.example.demo.model.Credenciales;
import com.example.demo.model.dto.CredencialesDTO;
import com.example.demo.service.CredencialesService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class CredencialesServiceImpl implements CredencialesService {

    @Autowired
    private CredencialesDAO credencialesDAO;


    @Override
    public String crear(Credenciales credenciales) throws Exception {
        validacionesCrear(credenciales);
        credencialesDAO.save(credenciales);
        return "Se agrego la credencial";
    }

    @Override
    public String actualizar(CredencialesDTO credencialesDTO) throws Exception {
        Credenciales credenciales = null;
        validacionesActualizar(credencialesDTO);
        credenciales = credencialesDAO.findById(credencialesDTO.getIdCredencial()).orElse(null);
        credenciales.setCuenta(credencialesDTO.getCuenta());
        credenciales.setPassword(credencialesDTO.getPassword());
        credenciales.setUrl(credencialesDTO.getUrl());
        credenciales.setPlataforma(credencialesDTO.getPlataforma());
        credenciales.setUsuarioModificador(credencialesDTO.getUsuarioModificador());
        credenciales.setFechaModificacion(credencialesDTO.getFechaModificacion());
        credencialesDAO.save(credenciales);
        return "Se actualizo la credencial";
    }

    @Override
    public void eliminar(Long idCredencial) throws Exception {
        if(idCredencial ==null){
            throw new Exception("Se debe ingresar el id de la credencial que desea eliminar");
        }
        if(!credencialesDAO.existsById(idCredencial)){
            throw new Exception("No se encontro la credencial con ese Id");
        }
        credencialesDAO.deleteById(idCredencial);
    }

    @Override
    public List<Credenciales> listar() throws Exception {
        return credencialesDAO.findAll();
    }
    private void validacionesCrear(Credenciales credenciales)throws Exception{
        if(credenciales.getCuenta() == null || credenciales.getCuenta().equals("")){
            throw new Exception("Debe ingresar el nombre o correo asociado a la cuenta");
        }
        if(Validaciones.isStringLenght(credenciales.getCuenta(),80)){
            throw new Exception("La cuenta o correo es largo, se aceptan solo 80 caracteres");
        }
        if(credenciales.getPassword() == null || credenciales.getPassword().equals("")){
            throw new Exception("Debe ingresar la contraseña");
        }
        if(Validaciones.isStringLenght(credenciales.getPassword(),80)){
            throw new Exception("La contraseña es largo, se aceptan solo 80 caracteres");
        }
        if(credenciales.getUrl() == null || credenciales.getUrl().equals("")){
            throw new Exception("Debe ingresar la url");
        }
        if(Validaciones.isStringLenght(credenciales.getUrl(),150)){
            throw new Exception("La url esta muy larga, se aceptan solo 150 caracteres");
        }
        if(!Validaciones.urlValidator(credenciales.getUrl())){
            throw new Exception("La url no es valida, ingrese una valida");
        }
        if(credenciales.getPlataforma() == null || credenciales.getPlataforma().equals("")){
            throw new Exception("Debe ingresar la plataforma a la cual pertenece la cuenta");
        }
        if(Validaciones.isStringLenght(credenciales.getPlataforma(),80)){
            throw new Exception("El nombre de la plataforma es muy largo, se aceptan solo 80 caracteres");
        }
        if(credenciales.getUsuarioCreador() ==null || credenciales.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario creador");
        }
        if(Validaciones.isStringLenght(credenciales.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador solo puede contener 50 caracteres ");
        }
        Date fechaActual = new Date();
        if(credenciales.getFechaCreacion()==null || credenciales.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar la fecha de creación");
        }
        if(credenciales.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
    }
    private void validacionesActualizar(CredencialesDTO credencialesDTO) throws Exception{
        if(credencialesDTO.getIdCredencial()==null){
            throw new Exception("Debe ingresar el id de la credencial para actualizarla");
        }
        if(!credencialesDAO.existsById(credencialesDTO.getIdCredencial())){
            throw new Exception("No se encontro ninguna credencial asociada a ese id");
        }
        if(credencialesDTO.getCuenta() == null || credencialesDTO.getCuenta().equals("")){
            throw new Exception("Debe ingresar el nombre o correo asociado a la cuenta");
        }
        if(Validaciones.isStringLenght(credencialesDTO.getCuenta(),80)){
            throw new Exception("La cuenta o correo es largo, se aceptan solo 80 caracteres");
        }
        if(credencialesDTO.getPassword() == null || credencialesDTO.getPassword().equals("")){
            throw new Exception("Debe ingresar la contraseña");
        }
        if(Validaciones.isStringLenght(credencialesDTO.getPassword(),80)){
            throw new Exception("La contraseña es largo, se aceptan solo 80 caracteres");
        }
        if(credencialesDTO.getUrl() == null || credencialesDTO.getUrl().equals("")){
            throw new Exception("Debe ingresar la url");
        }
        if(Validaciones.isStringLenght(credencialesDTO.getUrl(),150)){
            throw new Exception("La url esta muy larga, se aceptan solo 150 caracteres");
        }
        if(!Validaciones.urlValidator(credencialesDTO.getUrl())){
            throw new Exception("La url no es valida, ingrese una valida");
        }
        if(credencialesDTO.getPlataforma() == null || credencialesDTO.getPlataforma().equals("")){
            throw new Exception("Debe ingresar la plataforma a la cual pertenece la cuenta");
        }
        if(Validaciones.isStringLenght(credencialesDTO.getPlataforma(),80)){
            throw new Exception("El nombre de la plataforma es muy largo, se aceptan solo 80 caracteres");
        }
        if(credencialesDTO.getUsuarioModificador() ==null || credencialesDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el nombre del usuario modificador");
        }
        if(Validaciones.isStringLenght(credencialesDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador solo puede contener 50 caracteres ");
        }
        Date fechaActual = new Date();
        if(credencialesDTO.getFechaModificacion()==null || credencialesDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar la fecha de modificación");
        }
        if(credencialesDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
    }

}
