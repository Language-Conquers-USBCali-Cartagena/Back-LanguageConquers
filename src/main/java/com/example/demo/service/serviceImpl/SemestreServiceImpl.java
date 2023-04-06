package com.example.demo.service.serviceImpl;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.SemestreDAO;
import com.example.demo.model.Semestre;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.SemestreService;
import com.example.demo.util.Validaciones;
import org.aspectj.apache.bcel.generic.RET;
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
        return "Se creo exitosamente el semestre.";
    }
    @Override
    public String actualizar(Semestre semestre) throws Exception {
        validacionesActualizar(semestre);
        semestreDAO.save(semestre);
        return "Se actualizo exitosamente el semestre.";
    }
    @Override
    public String eliminar(Long idSemestre) throws Exception {
        if(idSemestre == null){
            throw new Exception("El id del semestre es obligatorio.");
        }
        if(!semestreDAO.existsById(idSemestre)){
            throw new Exception("No se encontró el semestre con ese id.");
        }
        if(!estudianteDAO.findByIdSemestre(idSemestre).isEmpty()){
            throw new Exception("No se puede eliminar el semestre porque esta asignado a un estudiante.");
        }
        semestreDAO.deleteById(idSemestre);
        return "Se elimino exitosamente el semestre.";
    }

    @Override
    public Semestre findById(Long idSemestre) throws Exception {
        if(idSemestre == null){
            throw  new Exception("Debe ingresar el id de un semestre.");
        }
        if(!semestreDAO.existsById(idSemestre)){
            throw new Exception("El semestre con id: " + idSemestre + " no existe.");
        }
        return semestreDAO.findById(idSemestre).get();
    }

    @Override
    public List<Semestre> listar() {
        return semestreDAO.findAll();
    }

    private void validacionesCrear(Semestre semestre)throws Exception{
        if(semestre.getNombre() == null || semestre.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del semestre.");
        }
        if(Validaciones.isStringLenght(semestre.getNombre(),50)){
            throw new Exception("El nombre del semestre no debe superar los 50 caracteres.");
        }
        if(semestre.getUsuarioCreador()==null || semestre.getUsuarioCreador().trim().equals("")){
            throw new Exception("Se debe ingresar un usuario creador.");
        }
        if(Validaciones.isStringLenght(semestre.getUsuarioCreador(),50)){
            throw new Exception("El usuario creador no debe de tener más de 50 caracteres.");
        }
        if(semestre.getFechaCreacion()==null){
            throw new Exception("Se debe ingresar una fecha válida.");
        }
        Date fechaActual = new Date();
        if(semestre.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }

    }
    private void validacionesActualizar(Semestre semestre)throws Exception{
        if(semestre.getIdSemestre() == null){
            throw new Exception("Debe ingresar un id del semestre que desea actualizar.");
        }
        if(!semestreDAO.existsById(semestre.getIdSemestre())){
            throw new Exception("No se encontró el semestre con ese id.");
        }
        if(semestre.getNombre() == null || semestre.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar el nombre del semestre.");
        }
        if(Validaciones.isStringLenght(semestre.getNombre(),50)){
            throw new Exception("El nombre del semestre no debe superar los 50 caracteres.");
        }
        if(semestre.getUsuarioModificador()==null || semestre.getUsuarioModificador().trim().equals("")){
            throw new Exception("Se debe ingresar un usuario modificador.");
        }
        if(Validaciones.isStringLenght(semestre.getUsuarioModificador(),50)){
            throw new Exception("El usuario modificador no debe de tener más de 50 caracteres.");
        }
        if(semestre.getFechaModificacion()==null){
            throw new Exception("Se debe ingresar una fecha válida.");
        }
        Date fechaActual = new Date();
        if(semestre.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
