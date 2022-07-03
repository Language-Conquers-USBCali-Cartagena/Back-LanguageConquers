package com.example.demo.service.serviceImpl;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.SemestreDAO;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Semestre;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.SemestreService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
    public String registrar(Semestre semestre) throws Exception {
        validacionesCrear(semestre);
        semestreDAO.save(semestre);
        return "Se creo el semestre satisfactoriamente";
    }
    @Override
    public String actualizar(SemestreDTO semestreDTO) throws Exception {
        Semestre semestre = null;
        validacionesActualizar(semestreDTO);
        semestre = semestreDAO.findById(semestreDTO.getIdSemestre()).orElse(null);
        semestre.setNombre(semestreDTO.getNombre());
        semestre.setUsuarioModificador(semestreDTO.getUsuarioModificador());
        semestre.setFechaModificacion(semestreDTO.getFechaModificacion());
        semestreDAO.save(semestre);
        return "Se actualizo el semestre satisfactoriamente";
    }
    @Override
    public void eliminar(Long idSemestre) throws Exception {
        if(idSemestre == null){
            throw new Exception("El Id del semestre es obligatorio");
        }
        if(!semestreDAO.existsById(idSemestre)){
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

    private void validacionesCrear(Semestre semestre)throws Exception{
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
        if(semestre.getFechaCreacion()==null || semestre.getFechaCreacion().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha valida");
        }
        Date fechaActual = new Date();
        if(semestre.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }

    }
    private void validacionesActualizar(SemestreDTO semestreDTO)throws Exception{
        if(semestreDTO.getIdSemestre() == null){
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
        if(semestreDTO.getFechaModificacion()==null || semestreDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha valida");
        }
        Date fechaActual = new Date();
        if(semestreDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido");
        }
    }
}
