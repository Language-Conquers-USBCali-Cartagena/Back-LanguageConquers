package com.example.demo.service.serviceImpl;

import com.example.demo.dao.PalabrasReservadasDAO;
import com.example.demo.dao.RetoDAO;
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
    List<PalabrasReservadasDTO>[] grupos = new ArrayList[10];
    Map<String, String> mapVariables = new HashMap<>();
    Map<String, String> mapMetodos = new HashMap<>();
    String respuesta = new String();
    Boolean esBasico = false;
    @Autowired
    PalabrasReservadasDAO palabrasReservadasDAO;
    @Autowired
    RetoDAO retoDAO;

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
    public String procesarPalabraReservada(List<PalabrasReservadasDTO> palabrasReservadasDTOs, Boolean esBasico) throws Exception {
        this.esBasico = esBasico;
        //Inicializa las listas
        inicializar();
        //Agrupa las listas segun su orden
        agrupar(palabrasReservadasDTOs);
        //Procesa las listas para ejecutar la accion esperada
        for(List<PalabrasReservadasDTO> grupo: grupos){
            procesarCategoria(grupo);
        }
        //Limpia las listas
        limpiar();
        return this.respuesta;

    }

    @Override
    public List<PalabrasReservadas> findByIdReto(Long idReto) throws Exception {
        if(!retoDAO.existsById(idReto)){
            throw new Exception("El reto no existe");
        }
        return palabrasReservadasDAO.findByIdReto(idReto);
    }

    private void inicializar(){
        for(int i = 0; i<10;i++){
            grupos[i] = new ArrayList<>();
        }
    }

    private void limpiar(){
        for(List<PalabrasReservadasDTO> grupo: grupos){
            grupo.clear();
        }
        mapVariables.clear();
        mapMetodos.clear();
    }

    private void agrupar(List<PalabrasReservadasDTO> palabrasReservadasDTOS) throws Exception{

        for (PalabrasReservadasDTO palabrasReservadasDTO: palabrasReservadasDTOS) {
            switch (palabrasReservadasDTO.getLista()){
                case 1:
                    grupos[0].add(palabrasReservadasDTO);
                    break;
                case 2:
                    grupos[1].add(palabrasReservadasDTO);
                    break;
                case 3:
                    grupos[2].add(palabrasReservadasDTO);
                    break;
                case 4:
                    grupos[3].add(palabrasReservadasDTO);
                    break;
                case 5:
                    grupos[4].add(palabrasReservadasDTO);
                    break;
                case 6:
                    grupos[5].add(palabrasReservadasDTO);
                    break;
                case 7:
                    grupos[6].add(palabrasReservadasDTO);
                    break;
                case 8:
                    grupos[7].add(palabrasReservadasDTO);
                    break;
                case 9:
                    grupos[8].add(palabrasReservadasDTO);
                    break;
                case 10:
                    grupos[9].add(palabrasReservadasDTO);
                    break;
                default:
                    throw new Exception("Orden no valido");
            }
        }
        for(int i = 0; i < grupos.length; i++){
            grupos[i] = grupos[i].stream().sorted(Comparator.comparingInt(PalabrasReservadasDTO::getOrden)).collect(Collectors.toList());
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
                    break;
                default:
                    throw new Exception("No puede inciar una linea con un " + grupo.get(0).getCategoria());
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
            if(palabrasReservadasDTOS.size() != 4){
                PalabrasReservadasDTO param3 = palabrasReservadasDTOS.get(3);
                PalabrasReservadasDTO param4 = palabrasReservadasDTOS.get(4);
                String respuestaMetodo = procesarGrupoMetodos(param2, param3, param4);
                mapVariables.put(palabraClave.getNombre(), respuestaMetodo);
            }else{
                PalabrasReservadasDTO param3 = palabrasReservadasDTOS.get(3);
                String respuestaMetodo = procesarGrupoMetodos(param2, param3);
                mapVariables.put(palabraClave.getNombre(), respuestaMetodo);
            }
        }else{
            mapVariables.put(palabraClave.getNombre(), param2.getNombre());
        }
     }

     private String procesarGrupoMetodos(List<PalabrasReservadasDTO> palabrasReservadasDTOS)throws Exception{
         PalabrasReservadasDTO palabraCalve = palabrasReservadasDTOS.get(0);
         PalabrasReservadasDTO param1 = palabrasReservadasDTOS.get(1);
         String resp = "";

         if(palabrasReservadasDTOS.size() >= 3){
             resp = tipoObjeto(palabraCalve, param1, palabrasReservadasDTOS.get(2));
             mapMetodos.put(param1.getNombre(), resp);
         }else {
             resp = tipoObjeto(palabraCalve, param1);
             mapMetodos.put(param1.getNombre(), resp);
         }
         return resp;
     }

    private String procesarGrupoMetodos(PalabrasReservadasDTO palabraCalve, PalabrasReservadasDTO param1)throws Exception{
        String resp = "";
        resp = tipoObjeto(palabraCalve, param1);
        mapMetodos.put(param1.getNombre(), resp);
        return resp;
    }
    private String procesarGrupoMetodos(PalabrasReservadasDTO palabraCalve, PalabrasReservadasDTO param1, PalabrasReservadasDTO param2)throws Exception{
        String resp = "";
        resp = tipoObjeto(palabraCalve, param1, param2);
        mapMetodos.put(param1.getNombre(), resp);
        return resp;
    }

    private String tipoObjeto(PalabrasReservadasDTO palabraCalve, PalabrasReservadasDTO param1, PalabrasReservadasDTO param2) throws  Exception {
        String variable = "";
        String variable2 = "";
        String resp = "";
        if (mapVariables.containsKey(param1.getNombre()) && param1.getCategoria().equalsIgnoreCase("variablecv")) {
            variable = mapVariables.get(param1.getNombre());
        } else if (param1.getCategoria().equalsIgnoreCase("objeto")) {
            variable = param1.getNombre();
        }else{
            throw new Exception("Parametro " + param1.getNombre()+ " no valido ");
        }
        if (mapVariables.containsKey(param2.getNombre()) && param2.getCategoria().equalsIgnoreCase("variablecv")) {
            variable2 = mapVariables.get(param2.getNombre());
        } else if (param2.getCategoria().equalsIgnoreCase("objeto")) {
            variable2 = param2.getNombre();
        }else{
            throw new Exception("Parametro " + param2.getNombre()+ " no valido ");
        }
        switch (palabraCalve.getNombre().toLowerCase()) {
            case "encender":
//                if((!mapMetodos.))
                if((!mapVariables.containsValue(variable) || !mapVariables.containsValue(variable2)) && !esBasico){
                    throw new Exception("Los parametros deben existir para poder encender fuego");
                }
                resp = MetodosPalabras.encender(variable, variable2);
                break;
            case "juntar":
                if(!mapVariables.containsValue(variable) || !mapVariables.containsValue(variable2)){
                    throw new Exception("Los parametros deben existir para poder juntarlos");
                }
                resp = MetodosPalabras.juntar(variable, variable2);
                break;
            case "construir":
                if(!mapVariables.containsValue(variable) || !mapVariables.containsValue(variable2)){
                    throw new Exception("Los parametros deben existir para poder construir algo");
                }
                resp = MetodosPalabras.construir(variable, variable2);
                break;
            default:
                throw new Exception("La palabra " +palabraCalve.getNombre() + " no corresponde a ningun metodo");
        }
        this.respuesta = resp;
        return resp;
    }
    private String tipoObjeto(PalabrasReservadasDTO palabraClave, PalabrasReservadasDTO param1) throws Exception{
        String variable = "";
        String resp = "";
        if(mapVariables.containsKey(param1.getNombre()) && param1.getCategoria().equalsIgnoreCase("variable")){
            variable = mapVariables.get(param1.getNombre());
        } else if (param1.getCategoria().equalsIgnoreCase("objeto")) {
            variable = param1.getNombre();
        }else{
            throw new Exception("Parametro " + param1.getNombre()+ " no valido ");
        }
        switch (palabraClave.getNombre().toLowerCase()) {
            case "buscar":
                if(param1.getNombre().equalsIgnoreCase("coco")){

                    if(mapMetodos.get("arbol") == null || !mapMetodos.get("arbol").equalsIgnoreCase("cima del arbol")){
                        throw new Exception("Para buscar un " + param1.getNombre() + " debe estar en una palmera");
                    }
                }
                resp = MetodosPalabras.buscar(variable);

                break;
            case "cortar":
                if(!mapMetodos.containsValue(param1.getNombre()) && esBasico){
                    throw new Exception("Debe haber encontrado el objeto para poder cortarlo");
                }
                if(!mapVariables.containsValue(param1.getNombre()) && !esBasico){
                    throw new Exception("El objeto debe existir para poder cortarlo");
                }
                resp = MetodosPalabras.cortar(variable);
                break;
            case "escalar":
                if(!mapMetodos.containsValue(param1.getNombre()) && esBasico){
                    throw new Exception("Debe haber encontrado el objeto para poder escalarlo");
                }
                if(!mapVariables.containsValue(param1.getNombre()) && !esBasico){
                    throw new Exception("El objeto debe existir para poder escalarlo");
                }
                resp = MetodosPalabras.escalar(variable);
                break;
            case "golpear":
                if(!mapMetodos.containsValue(param1.getNombre()) && esBasico){
                    throw new Exception("Debe haber encontrado el objeto para poder golpearlo");
                }
                if(!mapVariables.containsValue(param1.getNombre()) && !esBasico){
                    throw new Exception("El objeto debe existir para poder escalarlo");
                }
                resp = MetodosPalabras.golpear(variable);
                break;
            case "construir":
                if(!mapMetodos.containsValue(param1.getNombre()) && esBasico){
                    throw new Exception("Debe haber encontrado el objeto para poder construir");
                }
                if(!mapVariables.containsValue(param1.getNombre()) && !esBasico){
                    throw new Exception("El objeto debe existir para poder construir algo");
                }
                resp = MetodosPalabras.construir(variable);
                break;
            default:
                throw new Exception("La palabra " + palabraClave.getNombre() + " no corresponde a ningun metodo");
        }
        this.respuesta = resp;
        return resp;
    }


    private String procesarRespuestaBasica(List<PalabrasReservadasDTO> palabrasReservadasDTOS){

        return "Hola";
    }
}
