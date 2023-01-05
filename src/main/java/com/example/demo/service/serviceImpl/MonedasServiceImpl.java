package com.example.demo.service.serviceImpl;

import com.example.demo.dao.MonedasDAO;
import com.example.demo.model.Monedas;
import com.example.demo.model.dto.MonedasDTO;
import com.example.demo.service.MonedasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class MonedasServiceImpl implements MonedasService {
    //TODO: TERMINAR DE CREAR LOS SERVICIOS BASICOS COMO EDITAR Y ELIMINAR
    @Autowired
    MonedasDAO monedasDAO;


    @Override
    public List<Monedas> findAlList() throws Exception {
        return monedasDAO.findAll();
    }

    @Override
    public String registrar(Monedas monedas) throws Exception {
        try{
            monedasDAO.save(monedas);
            return "Se creo correctamente las monedas";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String actualizar(MonedasDTO monedasDTO) throws Exception {
        Monedas monedas = null;
        //Todo: Faltan validaciones
        monedas = monedasDAO.findById(monedasDTO.getIdMoneda()).orElse(null);
        monedas.setCantidad(monedasDTO.getCantidad());
        monedas.setImgMonedas(monedasDTO.getImgMonedas());
        monedas.setUsuarioCreador(monedasDTO.getUsuarioCreador());
        monedas.setFechaCreacion(monedasDTO.getFechaCreacion());
        monedas.setUsuarioModificador(monedasDTO.getUsuarioModificador());
        monedas.setFechaModificacion(monedasDTO.getFechaModificacion());
        monedasDAO.save(monedas);
        return "Se actualizo la moneda correctamente";
    }

    @Override
    public String eliminar(Long idMonedas) throws Exception {
        if(idMonedas == null){
            throw new Exception("Se debe ingresar el id de la moneda");
        }
        if(!monedasDAO.existsById(idMonedas)){
            throw new Exception("La moneda con id: "+idMonedas+ " no existe");
        }
        //Todo: Falta validacion de mision
        monedasDAO.deleteById(idMonedas);
        return "Se elimino exitosamente la moneda";
    }

    @Override
    public Monedas findById(Long idMonedas) throws Exception {
        if(idMonedas == null){
            throw new Exception("Se debe ingresar el id de la moneda");
        }
        if(!monedasDAO.existsById(idMonedas)){
            throw new Exception("La moneda con id: "+idMonedas+ " no existe");
        }
        return monedasDAO.findById(idMonedas).get();
    }
}
