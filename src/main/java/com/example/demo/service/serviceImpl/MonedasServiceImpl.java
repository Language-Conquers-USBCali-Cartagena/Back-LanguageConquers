package com.example.demo.service.serviceImpl;

import com.example.demo.dao.MisionDAO;
import com.example.demo.dao.MonedasDAO;
import com.example.demo.model.Monedas;
import com.example.demo.model.dto.MonedasDTO;
import com.example.demo.service.MonedasService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class MonedasServiceImpl implements MonedasService {
    @Autowired
    MonedasDAO monedasDAO;

    @Autowired
    MisionDAO misionDAO;

    @Override
    public List<Monedas> findAlList() throws Exception {
        return monedasDAO.findAll();
    }

    @Override
    public String registrar(Monedas monedas) throws Exception {
        try{
            validacionesCrear(monedas);
            monedasDAO.save(monedas);
            return "Se creo exitosamente la moneda.";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String actualizar(MonedasDTO monedasDTO) throws Exception {
        Monedas monedas = null;
        validacionesActualizar(monedasDTO);
        monedas = monedasDAO.findById(monedasDTO.getIdMoneda()).orElse(null);
        monedas.setCantidad(monedasDTO.getCantidad());
        monedas.setImgMonedas(monedasDTO.getImgMonedas());
        monedas.setUsuarioModificador(monedasDTO.getUsuarioModificador());
        monedas.setFechaModificacion(monedasDTO.getFechaModificacion());
        monedasDAO.save(monedas);
        return "Se actualizo exitosamente la moneda.";
    }

    @Override
    public String eliminar(Long idMonedas) throws Exception {
        if(idMonedas == null){
            throw new Exception("Se debe ingresar el id de la moneda.");
        }
        if(!monedasDAO.existsById(idMonedas)){
            throw new Exception("La moneda con id: "+idMonedas+ " no existe.");
        }
        if(!misionDAO.findByIdMoneda(idMonedas).isEmpty()){
            throw new Exception("No se puede eliminar la moneda por que esta siendo utilizada en una misión.");
        }
        monedasDAO.deleteById(idMonedas);
        return "Se elimino exitosamente la moneda.";
    }

    @Override
    public Monedas findById(Long idMonedas) throws Exception {
        if(idMonedas == null){
            throw new Exception("Se debe ingresar el id de la moneda.");
        }
        if(!monedasDAO.existsById(idMonedas)){
            throw new Exception("La moneda con id: "+idMonedas+ " no existe.");
        }
        return monedasDAO.findById(idMonedas).get();
    }

    private void validacionesCrear(Monedas monedas)throws Exception{
        if(monedas.getImgMonedas()==null || monedas.getImgMonedas().trim().equals("")){
            throw new Exception("Se debe ingresar una imagen de la moneda.");
        }
        if(Validaciones.isStringLenght(monedas.getImgMonedas(),250)){
            throw new Exception("La URL o nombre del archivo es muy largo, solo se aceptan 250 caracteres.");
        }
        if(monedas.getCantidad()<0){
            throw new Exception("Se debe ingresar el valor que representa la moneda y debe ser mayor a 0.");
        }
        if(monedas.getUsuarioCreador()==null || monedas.getUsuarioCreador().equals("")){
            throw new Exception("Debe ingresar el usuario creador.");
        }
        if(Validaciones.isStringLenght(monedas.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
        }
        if(monedas.getFechaCreacion()==null || monedas.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación.");
        }
    }

    private void validacionesActualizar(MonedasDTO monedasDTO)throws Exception{
        if(monedasDTO.getIdMoneda() == null || monedasDTO.getIdMoneda().toString().equals("")){
            throw new Exception("Se debe ingresar el id de la moneda que desea actualizar.");
        }
        if(monedasDTO.getIdMoneda()<0){
            throw new Exception("Se debe ingresar un id de la moneda válido.");
        }
        if(!monedasDAO.existsById(monedasDTO.getIdMoneda())){
            throw new Exception("No se encontró una moneda con ese id.");
        }
        if(monedasDTO.getImgMonedas()==null || monedasDTO.getImgMonedas().trim().equals("")){
            throw new Exception("Se debe ingresar una imagen de la moneda.");
        }
        if(Validaciones.isStringLenght(monedasDTO.getImgMonedas(),250)){
            throw new Exception("La URL o nombre del archivo es muy largo, solo se aceptan 250 caracteres.");
        }
        if(monedasDTO.getCantidad()<0){
            throw new Exception("Se debe ingresar el valor que representa la moneda y debe ser mayor a 0.");
        }
        if(monedasDTO.getUsuarioModificador()==null || monedasDTO.getUsuarioModificador().equals("")){
            throw new Exception("Debe ingresar el usuario modificador.");
        }
        if(Validaciones.isStringLenght(monedasDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.");
        }
        if(monedasDTO.getFechaModificacion()==null || monedasDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación.");
        }
        Date fechaActual = new Date();
        if(monedasDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
