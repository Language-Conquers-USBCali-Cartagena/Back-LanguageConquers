package com.example.demo.service.serviceImpl;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.SemestreDAO;
import com.example.demo.model.Semestre;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.SemestreService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class SemestreServiceImpl implements SemestreService {

    @Autowired
    private SemestreDAO semestreDAO;

    @Autowired
    private EstudianteDAO estudianteDAO;

    @Override
    public Semestre registrar(Semestre semestre) throws Exception {
        if(semestre.getNombre() == null || semestre.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del semestre");
        }
        if(Validaciones.isStringLenght(semestre.getNombre(),50)){
            throw new Exception("El nombre del semestre no debe superar los 50 caracteres");
        }
        if(semestre.getUsuarioCreador()==null || semestre.getUsuarioCreador().trim().equals("")){
            throw new Exception("Se debe ingresar un usuario creador");
        }
        if(Validaciones.isStringLenght(semestre.getUsuarioCreador(),50)){
            throw new Exception("El usuario creador no debe de tener más de 50 caracteres");
        }
        if(semestre.getFechaCreacion()==null || semestre.getFechaCreacion().equals("")){
            throw new Exception("Se debe ingresar una fecha valida");
        }
        Date fechaActual = new Date();
        if(semestre.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
        semestre.setNombre(semestre.getNombre());
        semestre.setUsuarioCreador(semestre.getUsuarioCreador());
        semestre.setFechaCreacion(semestre.getFechaCreacion());
        return semestreDAO.save(semestre);
    }

    @Override
    public Semestre actualizar(SemestreDTO semestreDTO) throws Exception {
        Semestre semestre = null;
        if(semestreDTO.getIdSemestre()==null || semestreDTO.getIdSemestre().equals("")){
            throw new Exception("Debe ingresar un Id del semestre que desea actualizar");
        }
        if(!semestreDAO.existsById(semestreDTO.getIdSemestre())){
            throw new Exception("No se encontro el semestre con ese Id");
        }
        if(semestreDTO.getNombre() == null || semestreDTO.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del semestre");
        }
        if(Validaciones.isStringLenght(semestreDTO.getNombre(),50)){
            throw new Exception("El nombre del semestre no debe superar los 50 caracteres");
        }
        if(semestreDTO.getUsuarioModificador()==null || semestreDTO.getUsuarioModificador().trim().equals("")){
            throw new Exception("Se debe ingresar un usuario modificador");
        }
        if(Validaciones.isStringLenght(semestreDTO.getUsuarioModificador(),50)){
            throw new Exception("El usuario modificador no debe de tener más de 50 caracteres");
        }
        if(semestreDTO.getFechaModificacion()==null || semestreDTO.getFechaModificacion().equals("")){
            throw new Exception("Se debe ingresar una fecha valida");
        }
        Date fechaActual = new Date();
        if(semestreDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
        semestre = semestreDAO.findById(semestreDTO.getIdSemestre()).get();
        semestre.setNombre(semestreDTO.getNombre());
        semestre.setUsuarioModificador(semestreDTO.getUsuarioModificador());
        semestre.setFechaModificacion(semestreDTO.getFechaModificacion());
        return semestreDAO.save(semestre);
    }

    @Override
    public void eliminar(Long idSemestre) throws Exception {
        if(idSemestre == null){
            throw new Exception("El Id del semestre es obligatorio");
        }
        if(semestreDAO.existsById(idSemestre) == false){
            throw new Exception("No se encontro el semestre con ese Id");
        }
        if(!estudianteDAO.findByIdSemestre(idSemestre).isEmpty()){
            throw new Exception("No se puede eliminar el semestre porque esta asignado a un estudiante");
        }
        semestreDAO.deleteById(idSemestre);
    }

    @Override
    public List<Semestre> listar() {
        return semestreDAO.findAll();
    }
}
