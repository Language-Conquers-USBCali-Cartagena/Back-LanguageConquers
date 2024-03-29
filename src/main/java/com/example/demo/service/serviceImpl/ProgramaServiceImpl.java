package com.example.demo.service.serviceImpl;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.ProgramaDAO;
import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class ProgramaServiceImpl implements ProgramaService {
    @Autowired
    private ProgramaDAO programaDAO;
    @Autowired
    private EstudianteDAO estudianteDAO;


    @Override
    public String registrar(Programa programa) throws Exception {
        validacionesCrear(programa);
        programaDAO.save(programa);
        return "Se creo exitosamente el programa.";
    }

    @Override
    public String actualizar(ProgramaDTO programaDTO) throws Exception {
        Programa programa = null;
        validacionesActualizar(programaDTO);
        programa = programaDAO.findById(programaDTO.getIdPrograma()).orElse(null);
        programa.setNombre(programaDTO.getNombre());
        programa.setUsuarioModificador(programaDTO.getUsuarioModificador());
        programa.setFechaModificacion(programaDTO.getFechaModificacion());
        programaDAO.save(programa);
        return "Se actualizo exitosamente el programa.";
    }

    @Override
    public String eliminar(Long idPrograma) throws Exception {
        if(idPrograma == null){
            throw new Exception("Debe ingresar el id del programa.");
        }
        if(!programaDAO.existsById(idPrograma)){
            throw new Exception("No se encontró el programa con ese id.");
        }
        if(!estudianteDAO.findByIdPrograma(idPrograma).isEmpty()){
            throw new Exception("No se puede eliminar el programa porque esta siendo utilizado en un estudiante.");
        }
        programaDAO.deleteById(idPrograma);
        return "Se elimino exitosamente el programa.";
    }

    @Override
    public Programa findById(Long idPrograma) throws Exception {
        if(idPrograma == null){
            throw new Exception("Debe ingresar el id del programa.");
        }
        if(!programaDAO.existsById(idPrograma)){
            throw new Exception("No se encontró el programa con ese id.");
        }
        return programaDAO.findById(idPrograma).get();
    }

    @Override
    public List<Programa> listar() {
        return programaDAO.findAll();
    }

    private void validacionesCrear(Programa programa)throws Exception{
        if(programa.getNombre() == null || programa.getNombre().equals("")){
            throw new Exception("Se debe ingresar un nombre del programa.");
        }
        if(Validaciones.isStringLenght(programa.getNombre(),50)){
            throw new Exception("Debe ingresar un nombre del programa no superior a 50 caracteres.");
        }
        if(programa.getUsuarioCreador()==null || programa.getUsuarioCreador().trim().equals("")){
            throw new Exception("Debe ingresar un usuario creador válido.");
        }
        if(Validaciones.isStringLenght(programa.getUsuarioCreador(),50)){
            throw new Exception("Debe ingresar un usuario creador no superior a 50 caracteres.");
        }
        if(programa.getFechaCreacion() == null){
            throw new Exception("Se debe ingresar una fecha de creación.");
        }
        Date fechaActual = new Date();
        if(programa.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
    private void validacionesActualizar(ProgramaDTO programaDTO)throws Exception{
        if(programaDTO.getIdPrograma() == null){
            throw new Exception("Debe ingresar el id del programa que desea actualizar.");
        }
        if(!programaDAO.existsById(programaDTO.getIdPrograma())){
            throw new Exception("No existe el programa con ese id.");
        }
        if(programaDTO.getNombre() == null || programaDTO.getNombre().trim().equals("")){
            throw new Exception("Se debe ingresar un nombre del programa.");
        }
        if(Validaciones.isStringLenght(programaDTO.getNombre(),50)){
            throw new Exception("Debe ingresar un nombre del programa no superior a 50 caracteres.");
        }
        if(programaDTO.getUsuarioModificador()==null || programaDTO.getUsuarioModificador().trim().equals("")){
            throw new Exception("Debe ingresar un usuario modificador válido.");
        }
        if(Validaciones.isStringLenght(programaDTO.getUsuarioModificador(),50)){
            throw new Exception("Debe ingresar un usuario modificador no superior a 50 caracteres.");
        }
        if(programaDTO.getFechaModificacion() == null){
            throw new Exception("Se debe ingresar una fecha de modificación.");
        }
        Date fechaActual = new Date();
        if(programaDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }
    }
}
