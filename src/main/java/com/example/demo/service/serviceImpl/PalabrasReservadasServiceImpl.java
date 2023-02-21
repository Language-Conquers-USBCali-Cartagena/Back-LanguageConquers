package com.example.demo.service.serviceImpl;

import com.example.demo.dao.PalabrasReservadasDAO;
import com.example.demo.model.PalabrasReservadas;
import com.example.demo.model.dto.PalabrasReservadasDTO;
import com.example.demo.service.PalabrasReservadasService;
import com.example.demo.util.MetodosPalabras;
import com.example.demo.util.Validaciones;
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
    String respuesta = new String();
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

        //Agrupa las listas segun su orden
        agrupar(palabrasReservadasDTOs);
        //Procesa las listas para ejecutar la accion esperada
        procesarCategoria(grupoA);
        procesarCategoria(grupoB);
        procesarCategoria(grupoC);
        procesarCategoria(grupoD);
        procesarCategoria(grupoE);
        procesarCategoria(grupoF);
        procesarCategoria(grupoG);
        procesarCategoria(grupoH);
        procesarCategoria(grupoI);
        procesarCategoria(grupoJ);

        limpiar();



        return this.respuesta;

    }

    private void limpiar() throws Exception{
        grupoA.clear();
        grupoB.clear();
        grupoC.clear();
        grupoD.clear();
        grupoE.clear();
        grupoF.clear();
        grupoG.clear();
        grupoH.clear();
        grupoI.clear();
        grupoJ.clear();
    }

    private void agrupar(List<PalabrasReservadasDTO> palabrasReservadasDTOS) throws Exception{
        for (PalabrasReservadasDTO palabrasReservadasDTO: palabrasReservadasDTOS) {
            switch (palabrasReservadasDTO.getLista()){
                case 1:
                    grupoA.add(palabrasReservadasDTO);
                    break;
                case 2:
                    grupoB.add(palabrasReservadasDTO);
                    break;
                case 3:
                    grupoC.add(palabrasReservadasDTO);
                    break;
                case 4:
                    grupoD.add(palabrasReservadasDTO);
                    break;
                case 5:
                    grupoE.add(palabrasReservadasDTO);
                    break;
                case 6:
                    grupoF.add(palabrasReservadasDTO);
                    break;
                case 7:
                    grupoG.add(palabrasReservadasDTO);
                    break;
                case 8:
                    grupoH.add(palabrasReservadasDTO);
                    break;
                case 9:
                    grupoI.add(palabrasReservadasDTO);
                    break;
                case 10:
                    grupoJ.add(palabrasReservadasDTO);
                    break;
                default:
                    throw new Exception("Orden no valido");
            }
            grupoA.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoB.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoC.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoD.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoE.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoF.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoG.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoH.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoI.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
            grupoJ.stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
        }
    }
    private void procesarCategoria(List<PalabrasReservadasDTO> grupo) throws Exception{

        if(!grupo.isEmpty() ){
            switch (grupo.get(0).getCategoria().toLowerCase()){
                case "variable":
                    procesarGrupoVariables(grupo);
                    break;
                case "bucle":
                    //TODO: Metodo del bucle
                    break;
                case "condicional":
                    //TODO: Metodo condicional
                    break;
                case "metodo":
                    procesarGrupoMetodos(grupo);
            }
        }
    }
     private void procesarGrupoVariables(List<PalabrasReservadasDTO> palabrasReservadasDTOS) throws Exception{

        PalabrasReservadasDTO palabraClave = palabrasReservadasDTOS.get(0);
        PalabrasReservadasDTO param1 = palabrasReservadasDTOS.get(1);
        PalabrasReservadasDTO param2 = palabrasReservadasDTOS.get(2);
        if(!param1.getCategoria().equalsIgnoreCase("logica")){
            throw new Exception("Para asignar un valor a una variable debe utilizar un =");
        }

        if(param2.getCategoria().equalsIgnoreCase("metodo")){
            PalabrasReservadasDTO param3 = palabrasReservadasDTOS.get(3);
            String respuestaMetodo = procesarGrupoMetodos(param2, param3);
            mapVariables.put(palabraClave.getNombre(), respuestaMetodo);

        }else{
            mapVariables.put(palabraClave.getNombre(), param2.getNombre());
        }
     }

     private String procesarGrupoMetodos(List<PalabrasReservadasDTO> palabrasReservadasDTOS)throws Exception{
         PalabrasReservadasDTO palabraCalve = palabrasReservadasDTOS.get(0);
         PalabrasReservadasDTO param1 = palabrasReservadasDTOS.get(1);
         String resp = "";

         resp = tipoObjeto(palabraCalve, param1);
         return resp;
     }

    private String procesarGrupoMetodos(PalabrasReservadasDTO palabraCalve, PalabrasReservadasDTO param1)throws Exception{
        String resp = "";
        resp = tipoObjeto(palabraCalve, param1);
        return resp;
    }

    private String tipoObjeto(PalabrasReservadasDTO palabraClave ,PalabrasReservadasDTO param1) throws Exception{
        String variable = "";
        String resp = "";
        if(mapVariables.containsKey(param1.getNombre()) && param1.getCategoria().equalsIgnoreCase("variablecv")){
            variable = mapVariables.get(param1.getNombre());
        } else if (param1.getCategoria().equalsIgnoreCase("objeto")) {
            variable = param1.getNombre();
        }
        switch (palabraClave.getNombre().toLowerCase()) {
            case "buscar":
                resp = MetodosPalabras.buscar(variable);
                break;
            case "cortar":
                resp = MetodosPalabras.cortar(variable);
                break;
            case "escalar":
                resp = MetodosPalabras.escalar(variable);
                break;
            default:
                throw new Exception("La palabra no corresponde a ningun metodo");
        }
        this.respuesta = resp;
        return resp;
    }
    //TODO: Deben Existir en la bd las siguientes categorias para las palabras reservadas: Metodo, Variable, VariableCV, Bucle, Condicional, logicas, objetos


}
