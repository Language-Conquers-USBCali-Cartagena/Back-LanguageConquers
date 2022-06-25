package com.example.demo.service.serviceImpl;

import com.example.demo.dao.GrupoDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.model.Grupo;
import com.example.demo.model.dto.GrupoDTO;
import com.example.demo.service.GrupoService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoDAO grupoDAO;

    @Autowired
    private RetoEstudianteDAO retoEstudianteDAO;

    @Override
    public String registrar(Grupo grupo) throws Exception {
        if(grupo.getNombre()==null || grupo.getNombre().trim().equals("")){
            throw new Exception("Debe ingresar el nombre del grupo");
        }
        if(Validaciones.isStringLenght(grupo.getNombre(),50)){
            throw new Exception("El nombre del grupo no debe contener más de 50 caracteres");
        }
        if(grupo.getUsuarioCreador()==null || grupo.getUsuarioCreador().trim().equals("")){
            throw new Exception("Debe ingresar un usuario creador");
        }
        if(Validaciones.isStringLenght(grupo.getUsuarioCreador(),50)){
            throw new Exception("El nombre del usuario creador no debe contener más de 50 caracteres");
        }
        if(grupo.getFechaCreacion()==null || grupo.getFechaCreacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de creación");
        }
        Date fechaActual = new Date();
        if(grupo.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No se puede ingresar una fecha que aun no ha sucedido");
        }
        grupoDAO.save(grupo);
        return "Se registro el grupo satisfactoriamente";
    }

    @Override
    public String actualizar(GrupoDTO grupoDTO) throws Exception {
        Grupo grupo = null;
        if(grupoDTO.getNombre()==null || grupoDTO.getNombre().trim().equals("")){
            throw new Exception("Debe ingresar el nombre del grupo");
        }
        if(Validaciones.isStringLenght(grupoDTO.getNombre(),50)){
            throw new Exception("El nombre del grupo no debe contener más de 50 caracteres");
        }
        if(grupoDTO.getUsuarioModificador()==null || grupoDTO.getUsuarioModificador().trim().equals("")){
            throw new Exception("Debe ingresar un usuario modificador");
        }
        if(Validaciones.isStringLenght(grupoDTO.getUsuarioModificador(),50)){
            throw new Exception("El nombre del usuario modificador no debe contener más de 50 caracteres");
        }
        if(grupoDTO.getFechaModificacion()==null || grupoDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Debe ingresar una fecha de modificación");
        }
        Date fechaActual = new Date();
        if(grupoDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No se puede ingresar una fecha que aun no ha sucedido");
        }
        grupo = grupoDAO.findById(grupoDTO.getIdGrupo()).orElse(null);
        grupo.setNombre(grupoDTO.getNombre());
        grupo.setUsuarioModificador(grupoDTO.getUsuarioModificador());
        grupo.setFechaModificacion(grupoDTO.getFechaModificacion());
        grupoDAO.save(grupo);
        return "Se actualizo el grupo satisfactoriamente";
    }

    @Override
    public void eliminar(Long idGrupo) throws Exception {
        if(idGrupo == null){
            throw new Exception("El Id del grupo es obligatorio");
        }
        if(!grupoDAO.existsById(idGrupo)){
            throw new Exception("No se encontro un grupo con ese Id");
        }
        if(!retoEstudianteDAO.findByIdGrupo(idGrupo).isEmpty()){
            throw new Exception("No se puede eliminar el grupo ya que esta asignado aun reto estudiante");
        }
        grupoDAO.deleteById(idGrupo);
    }

    @Override
    public List<Grupo> listar() {
        return grupoDAO.findAll();
    }
}
