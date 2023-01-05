package com.example.demo.service.serviceImpl;

import com.example.demo.dao.MisionDAO;
import com.example.demo.model.*;
import com.example.demo.model.dto.MisionDTO;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class MisionServiceImpl implements MisionService {

    //TODO: TERMINAR DE CREAR LOS SERVICIOS BASICOS COMO EDITAR Y ELIMINAR
    @Autowired
    MisionDAO misionDAO;

    @Autowired
    NivelMisionService nivelMisionService;

    @Autowired
    CursoService cursoService;

    @Autowired
    TipoMisionService tipoMisionService;

    @Autowired
    MonedasService monedasService;
    @Override
    public List<Mision> ListarMisiones() throws Exception {
        return misionDAO.findAll();
    }

    @Override
    public String registrar(Mision mision) throws Exception {
        try{
            misionDAO.save(mision);

            return "Se creo la mision";

        }catch (Exception e){
            throw  new Exception("No se pudo crear la mision");
        }
    }

    @Override
    public String actualizar(MisionDTO misionDTO) throws Exception {
        Mision mision = null;
        NivelMision idNivelMision = null;
        Curso idCurso = null;
        TipoMision idTipoMision = null;
        Monedas idMoneda = null;

        //Todo: falta validaciones

        mision = misionDAO.findById(misionDTO.getIdMision()).orElse(null);
        mision.setNombre(misionDTO.getNombre());
        idNivelMision = nivelMisionService.findById(misionDTO.getIdNivelMision());
        mision.setNivelMision(idNivelMision);
        idCurso = cursoService.findById(misionDTO.getIdCurso());
        mision.setCurso(idCurso);
        idTipoMision = tipoMisionService.findById(misionDTO.getIdTipoMision());
        mision.setTipoMision(idTipoMision);
        idMoneda = monedasService.findById(misionDTO.getIdMonedas());
        mision.setMonedas(idMoneda);
        mision.setUsuarioCreador(misionDTO.getUsuarioCreador());
        mision.setFechaCreacion(misionDTO.getFechaCreacion());
        misionDTO.setUsuarioModificador(misionDTO.getUsuarioModificador());
        misionDAO.save(mision);
        return "Se actualizo la misión correctamente";
    }

    @Override
    public String eliminar(Long idMision) throws Exception {
        if(idMision == null){
            throw new Exception("Se debe ingresar el id de la mision");
        }
        if(!misionDAO.existsById(idMision)){
            throw new Exception("La mision con id: "+ idMision + " no existe");
        }
        //Todo: falta validacion de reto y mision_estudiante
        misionDAO.deleteById(idMision);
        return "La mision de ha eliminado exitosamente";
    }

    @Override
    public Mision findById(Long idMision) throws Exception {
        if(idMision == null){
            throw new Exception("Debe ingresar el id de una mision");
        }
        if(!misionDAO.existsById(idMision)){
            throw new Exception("La mision con id: " + idMision + " no existe");
        }
        return misionDAO.findById(idMision).get();
    }
}
