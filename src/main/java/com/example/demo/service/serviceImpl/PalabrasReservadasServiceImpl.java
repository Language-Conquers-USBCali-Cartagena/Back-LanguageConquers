package com.example.demo.service.serviceImpl;

import com.example.demo.dao.PalabrasReservadasDAO;
import com.example.demo.model.PalabrasReservadas;
import com.example.demo.model.dto.PalabrasReservadasDTO;
import com.example.demo.service.PalabrasReservadasService;
import com.example.demo.util.MetodosPalabras;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Scope("singleton")
@Service
public class PalabrasReservadasServiceImpl implements PalabrasReservadasService {
    List<PalabrasReservadasDTO> grupoA = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoB = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoC = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoD = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoE = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoF = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoG = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoH = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoI = new ArrayList<>();
    List<PalabrasReservadasDTO> grupoJ = new ArrayList<>();
    Map<String, String> mapVariables = new HashMap<>();

    @Autowired
    PalabrasReservadasDAO palabrasReservadasDAO;


    @Override
    public List<PalabrasReservadas> findAll() throws Exception {
        return palabrasReservadasDAO.findAll();
    }

    @Override
    public String crearPalabraResevada(PalabrasReservadas palabrasReservadas) throws Exception {
        try{
            palabrasReservadasDAO.save(palabrasReservadas);
            return "Se ha creado correctamente la palabra reservada.";
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    public String procesarPalabraReservada(List<PalabrasReservadasDTO> palabrasReservadasDTOs) throws Exception {
        agrupar(palabrasReservadasDTOs);
        if(!grupoA.isEmpty() ){
            switch (grupoA.get(0).getCategoria()){
                case "variable":
                    procesarGrupoVariables(grupoA);
                    break;
                case "bucle":
                    //TODO: Metodo del bucle
                    break;
                case "condicional":
                    //TODO: Metodo condicional
                    break;
                case "metodo":
                    //TODO: Metodo que procese los metodos
            }

        }

        return null;

    }


    private void agrupar(List<PalabrasReservadasDTO> palabrasReservadasDTOS) throws Exception{
        for (PalabrasReservadasDTO palabrasReservadasDTO: palabrasReservadasDTOS) {
            switch (palabrasReservadasDTO.getLista()){
                case 1:
                    grupoA.add(palabrasReservadasDTO);
                    grupoA.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 2:
                    grupoB.add(palabrasReservadasDTO);
                    grupoB.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 3:
                    grupoC.add(palabrasReservadasDTO);
                    grupoC.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 4:
                    grupoD.add(palabrasReservadasDTO);
                    grupoD.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 5:
                    grupoE.add(palabrasReservadasDTO);
                    grupoE.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 6:
                    grupoF.add(palabrasReservadasDTO);
                    grupoF.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 7:
                    grupoG.add(palabrasReservadasDTO);
                    grupoG.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 8:
                    grupoH.add(palabrasReservadasDTO);
                    grupoH.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 9:
                    grupoI.add(palabrasReservadasDTO);
                    grupoI.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                case 10:
                    grupoJ.add(palabrasReservadasDTO);
                    grupoJ.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
                    break;
                default:
                    throw new Exception("Orden no valido");
            }
        }
    }
     private void procesarGrupoVariables(List<PalabrasReservadasDTO> palabrasReservadasDTOS) throws Exception{

        PalabrasReservadasDTO palabraClave = palabrasReservadasDTOS.get(0);
        PalabrasReservadasDTO param1 = palabrasReservadasDTOS.get(1);
        PalabrasReservadasDTO param2 = palabrasReservadasDTOS.get(2);
        PalabrasReservadasDTO param3 = palabrasReservadasDTOS.get(3);

        if(param3.getCategoria().equalsIgnoreCase("metodo")){
            PalabrasReservadasDTO param4 = palabrasReservadasDTOS.get(4);
            procesarGrupoMetodos(param3, param4);

        }else{
            mapVariables =  MetodosPalabras.variable(param1.getNombre(), param2.getNombre(), param3.getNombre());

        }



     }

     private String procesarGrupoMetodos(List<PalabrasReservadasDTO> palabrasReservadasDTOS)throws Exception{
         PalabrasReservadasDTO palabraCalve = palabrasReservadasDTOS.get(0);
         PalabrasReservadasDTO param1 = palabrasReservadasDTOS.get(1);
         String respuesta = "";
         switch (palabraCalve.getNombre().toLowerCase()){
             case "buscar":
                 respuesta = MetodosPalabras.buscar(param1.getNombre());
                 break;
             case "cortar":
                 respuesta = MetodosPalabras.cortar(param1.getNombre());
                 break;
             default:
                 throw new Exception("La palabra no corresponde a ningun metodo");
         }
         return respuesta;
     }

    private String procesarGrupoMetodos(PalabrasReservadasDTO palabraCalve, PalabrasReservadasDTO param1)throws Exception{

        String respuesta = "";
        switch (palabraCalve.getNombre().toLowerCase()){
            case "buscar":
                respuesta = MetodosPalabras.buscar(param1.getNombre());
                break;
            case "cortar":
                respuesta = MetodosPalabras.cortar(param1.getNombre());
                break;
            default:
                throw new Exception("La palabra no corresponde a ningun metodo");
            //TODO: Completar los otros metodos posibles
        }
        return respuesta;
    }
}
