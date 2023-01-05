package com.example.demo.service.serviceImpl;

import com.example.demo.dao.RetoDAO;
import com.example.demo.model.Curso;
import com.example.demo.model.Estado;
import com.example.demo.model.Mision;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.service.EstadoService;
import com.example.demo.service.MisionService;
import com.example.demo.service.RetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class RetoServiceImpl implements RetoService {

    //TODO: Faltan validacones para los metodos de crear y actualizar
    @Autowired
    RetoDAO retoDAO;

    @Autowired
    MisionService misionService;

    @Autowired
    EstadoService estadoService;

    @Autowired
    CursoService cursoService;

    @Override
    public List<Reto> listReto() throws Exception{
        return retoDAO.findAll();
    }

    @Override
    public String registrar(Reto reto) throws Exception {
        //TODO:FALTAN VALIDACIONES
        try{
            retoDAO.save(reto);
            return "Se creo correctamente el reto";
        }catch (Exception e){
            throw new Exception("EL reto no se pudo crear el reto");
        }
    }

    @Override
    public String actualizar(RetoDTO retoDTO) throws Exception {
        Reto reto = null;
        Mision idMision = null;
        Estado idEstado = null;
        Curso idCurso = null;
        //TODO:FALTAN VALIDACIONES
        reto = retoDAO.findById(retoDTO.getIdReto()).orElse(null);
        reto.setNombreReto(retoDTO.getNombreReto());
        reto.setDescripcion(retoDTO.getDescripcion());
        reto.setMaximoIntentos(retoDTO.getMaximoIntentos());
        reto.setFechaInicio(retoDTO.getFechaInicio());
        reto.setFechaLimite(retoDTO.getFechaLimite());
        reto.setEsGrupal(retoDTO.isEsGrupal());
        reto.setNrEstudiatesGrupo(retoDTO.getNrEstudiatesGrupo());
        idMision = misionService.findById(retoDTO.getIdMision());
        reto.setMision(idMision);
        idEstado = estadoService.findById(retoDTO.getIdEstado());
        reto.setEstado(idEstado);
        idCurso = cursoService.findById(retoDTO.getIdCurso());
        reto.setCurso(idCurso);
        retoDAO.save(reto);
        return "Se actualizo el reto";
    }

    @Override
    public String eliminar(Long idReto) throws Exception {
        if(idReto == null){
            throw new Exception("El id del reto no existe");
        }
        if(!retoDAO.existsById(idReto)){
            throw new Exception("No se encontro un reto con el id: " + idReto);
        }
        //TODO: FALTA VALIDACION DE SI EXISTE EN ORDEN_RETO_IDE, EN RETO_ESTUDIANTE Y EN ROL
        retoDAO.deleteById(idReto);
        return "El reto se elimino exitosamente";
    }

    @Override
    public Reto findById(Long idReto) throws Exception {
        if(idReto == null){
            throw new Exception("Debe ingresar el id de un reto");
        }
        if(!retoDAO.existsById(idReto)){
            throw new Exception("El reto con id: " + idReto + " no existe");
        }
        return retoDAO.findById(idReto).get();
    }
}
