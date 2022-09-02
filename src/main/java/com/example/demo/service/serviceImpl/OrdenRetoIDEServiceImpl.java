package com.example.demo.service.serviceImpl;

import com.example.demo.dao.OrdenRetoIDEDAO;
import com.example.demo.dao.PalabrasReservadasDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.OrdenRetoIDEMapper;
import com.example.demo.model.OrdenRetoIDE;
import com.example.demo.model.dto.OrdenRetoIDEDTO;
import com.example.demo.service.OrdenRetoIDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Scope("singleton")
@Service
public class OrdenRetoIDEServiceImpl implements OrdenRetoIDEService {

    @Autowired
    private OrdenRetoIDEDAO ordenRetoIDEDAO;

    @Autowired
    private OrdenRetoIDEMapper ordenRetoIDEMapper;

    @Autowired
    PalabrasReservadasDAO palabrasReservadasDAO;
    @Autowired
    RetoDAO retoDAO;
    @Override
    public List<OrdenRetoIDEDTO> VerificarOrden(List<OrdenRetoIDEDTO> intento, Long idReto) throws Exception {

        //Aqui se trae el orden de como debe ser resuelto el ejercicio
        List<OrdenRetoIDE> correto =  ordenRetoIDEDAO.encontrarPorIdReto(idReto);
        List<OrdenRetoIDEDTO>correctoDTO = ordenRetoIDEMapper.toDTOList(correto);

        //Aqui se ordenna las listas para luego ser comparadas
        List<OrdenRetoIDEDTO> ordenCorretoOrdenado = correctoDTO.stream().sorted(Comparator.comparingInt(OrdenRetoIDEDTO::getOrden)).collect(Collectors.toList());
        List<OrdenRetoIDEDTO> intentoOrdenado = intento.stream().sorted(Comparator.comparingInt(OrdenRetoIDEDTO::getOrden)).collect(Collectors.toList());

        //Aqui se comparan la listas y se devuelve una lista con los errores
        List<OrdenRetoIDEDTO> error = new ArrayList<>();
        for (int i = 0; i < ordenCorretoOrdenado.size(); i++) {

            OrdenRetoIDEDTO ordenRetoCorrecto = ordenCorretoOrdenado.get(i);
            OrdenRetoIDEDTO ordenRetoIntento = null;
            if(i < intentoOrdenado.size()){
                ordenRetoIntento = intentoOrdenado.get(i);
            }else{
                ordenRetoIntento = new OrdenRetoIDEDTO();
            }

            if((ordenRetoCorrecto.getIdPalabraReservada() != ordenRetoIntento.getIdPalabraReservada() ||
                     ordenRetoCorrecto.getOrden() != ordenRetoIntento.getOrden())
                    && ordenRetoIntento.getOrden() != 0 ){
                error.add(ordenRetoIntento);
            }
        }
        for (OrdenRetoIDEDTO ordenRetoIDEDTO: ordenCorretoOrdenado) {
            System.out.println(ordenRetoIDEDTO.getOrden());
            System.out.println(ordenRetoIDEDTO.getIdPalabraReservada());
            System.out.println("-------------------------------------");
        }
        return error;
    }

    @Override
    public List<OrdenRetoIDE> findAll() throws Exception {
        return ordenRetoIDEDAO.findAll();
    }

    @Override
    public String crarOrdenRet(OrdenRetoIDE ordenRetoIDE) throws Exception {

//        validaciones(ordenRetoIDE);
        System.out.println("Id Orden: " + ordenRetoIDE.getIdOrdenRetoIDE());
        System.out.println("REto" + ordenRetoIDE.getReto().getIdReto());
        System.out.println("Orden" + ordenRetoIDE.getOrden());
        System.out.println(ordenRetoIDE.getFechaCreacion());
        System.out.println(ordenRetoIDE.getFechaModificacion());
        System.out.println("Palabra Reservada: " + ordenRetoIDE.getPalabrasReservadas());
        System.out.println(ordenRetoIDE.getUsuarioCreador());
        System.out.println(ordenRetoIDE.getUsuarioModificador());

        ordenRetoIDEDAO.save(ordenRetoIDE);
        return "Se creo correctamente el orden reto ide";

    }

    @Override
    public List<OrdenRetoIDE> findByIdReto(Long idREto) throws Exception {
        if(!retoDAO.existsById(idREto)){
            throw new Exception("El id reto no existe");
        }
        return ordenRetoIDEDAO.encontrarPorIdReto(idREto);
    }

    void validaciones (OrdenRetoIDE ordenRetoIDE) throws Exception{
        if(ordenRetoIDE.getOrden() == 0){
            throw new Exception("Debe ingresar un orden");
        }
        if(retoDAO.existsById(ordenRetoIDE.getReto().getIdReto())){
            throw new Exception("El id reto no existe  " + ordenRetoIDE.getReto().getIdReto());
        }
        if(palabrasReservadasDAO.existsById(ordenRetoIDE.getPalabrasReservadas().getIdPalabraReservada())){
            throw new Exception("El id reto no existe");
        }

    }

}
