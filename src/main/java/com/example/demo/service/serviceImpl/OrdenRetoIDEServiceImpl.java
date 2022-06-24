package com.example.demo.service.serviceImpl;

import com.example.demo.dao.OrdenRetoIDEDAO;
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
                    ordenRetoCorrecto.getPadre() != ordenRetoIntento.getPadre()) && ordenRetoIntento.getOrden() != 0 ){
                error.add(ordenRetoIntento);
            }
        }

        return error;
    }
}
