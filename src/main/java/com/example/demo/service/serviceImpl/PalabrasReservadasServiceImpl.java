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
    Integer posicion = 0;
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
        // Reinicia la posicion a 0
        this.posicion = 0;
        //Procesa las listas para ejecutar la accion esperada
        for(List<PalabrasReservadasDTO> grupo: grupos){
            this.posicion = this.posicion + 1;
            procesarCategoria(grupo);
        }
        //Limpia las listas
        String respuesta = this.respuesta;
        limpiar();
        return respuesta;

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
        respuesta = "";
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
                    if(grupo.size() < 3){
                        throw new Exception("Error en la linea "+this.posicion +": La estructura para asignar una variable es la siguiente: Variable = Objeto");
                    }
                    procesarGrupoVariables(grupo);
                    break;
                case "bucle":
                    //TODO: Metodo del bucle
                    break;
                case "condicional":
                    //TODO: Metodo condicional
                    break;
                case "metodo":
                    if(grupo.size() < 2){
                        throw new Exception("Error en la linea "+this.posicion +": La estructura para ejecutar una acción es la siguiente: Acción + Objeto/Variable con valor.");
                    }
                    procesarGrupoMetodos(grupo);
                    break;
                default:
                    throw new Exception("Error en la linea "+this.posicion +": No puede inciar una linea con un " + grupo.get(0).getCategoria());
            }
        }
    }
     private void procesarGrupoVariables(List<PalabrasReservadasDTO> palabrasReservadasDTOS) throws Exception{

        PalabrasReservadasDTO palabraClave = palabrasReservadasDTOS.get(0);
        PalabrasReservadasDTO param1 = palabrasReservadasDTOS.get(1);
        PalabrasReservadasDTO param2 = palabrasReservadasDTOS.get(2);
        if(!param1.getCategoria().equalsIgnoreCase("logica")){
            throw new Exception("Error en la linea "+this.posicion +": Para asignar un valor a una variable debe utilizar un =");
        }
        if(param2.getCategoria().equalsIgnoreCase("objeto")){
            throw new Exception("Error en la linea "+this.posicion +": Debe ejecutar una acción con el obejeto.");
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
            throw new Exception("Error en la linea "+this.posicion +": Parametro " + param1.getNombre()+ " no valido ");
        }
        if (mapVariables.containsKey(param2.getNombre()) && param2.getCategoria().equalsIgnoreCase("variablecv")) {
            variable2 = mapVariables.get(param2.getNombre());
        } else if (param2.getCategoria().equalsIgnoreCase("objeto")) {
            variable2 = param2.getNombre();
        }else{
            throw new Exception("Error en la linea "+this.posicion +": Parametro " + param2.getNombre()+ " no valido ");
        }
        variable = variable.toLowerCase();
        variable2 = variable2.toLowerCase();
        switch (palabraCalve.getNombre().toLowerCase()) {

            case "encender":
                if((!mapMetodos.containsValue(variable) || !mapMetodos.containsValue(variable2) && esBasico)){
                    throw new Exception("Error en la linea "+this.posicion +": Los parametros deben existir para poder encender fuego");
                }
                if((!mapVariables.containsValue(variable) || !mapVariables.containsValue(variable2)) && !esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": Los parametros deben existir para poder encender fuego");
                }
                resp = MetodosPalabras.encender(variable, variable2);
                break;
            case "juntar":
                if((!mapMetodos.containsValue(variable) || !mapMetodos.containsValue(variable2) && esBasico)){
                    throw new Exception("Error en la linea "+this.posicion +": Los parametros deben existir para poder juntarlos");
                }
                if((!mapVariables.containsValue(variable) || !mapVariables.containsValue(variable2)) && !esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": Los parametros deben existir para poder juntarlos");
                }
                resp = MetodosPalabras.juntar(variable, variable2);
                break;
            case "construir":
                if((!mapMetodos.containsValue(variable) || !mapMetodos.containsValue(variable2) && esBasico)){
                    throw new Exception("Error en la linea "+this.posicion +": Los parametros deben existir para poder construir algo");
                }
                if((!mapVariables.containsValue(variable) || !mapVariables.containsValue(variable2)) && !esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": Los parametros deben existir para poder construir algo");
                }
                resp = MetodosPalabras.construir(variable, variable2);
                break;
            default:
                throw new Exception("Error en la linea "+this.posicion +": La palabra " +palabraCalve.getNombre() + " no corresponde a ningun metodo");
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
            throw new Exception("Error en la linea "+this.posicion +": Parametro " + param1.getNombre()+ " no valido ");
        }

        switch (palabraClave.getNombre().toLowerCase()) {
            case "buscar":
                if(param1.getNombre().equalsIgnoreCase("coco")){

                    if(mapMetodos.get("Arbol") == null || !mapMetodos.get("Arbol").equalsIgnoreCase("cima del arbol")){
                        throw new Exception("Error en la linea "+this.posicion +": Para buscar un " + param1.getNombre() + " debe estar en una palmera");
                    }
                }
                resp = MetodosPalabras.buscar(variable);

                break;
            case "cortar":
                if(!mapMetodos.containsValue(param1.getNombre().toLowerCase()) && esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": Debe haber encontrado el/la "+param1.getNombre() +" para poder cortarlo");
                }
                if(!mapVariables.containsValue(param1.getNombre().toLowerCase()) && !esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": El " + param1.getNombre()+" debe existir para poder cortarlo");
                }
                resp = MetodosPalabras.cortar(variable);
                break;
            case "escalar":
                if(!mapMetodos.containsValue(param1.getNombre().toLowerCase()) && esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": Debe haber encontrado el/la " +param1.getNombre() +" para poder escalarlo");
                }
                if(!mapVariables.containsValue(param1.getNombre().toLowerCase()) && !esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": El "+param1.getNombre() +" debe existir para poder escalarlo");
                }
                resp = MetodosPalabras.escalar(variable);
                break;
            case "golpear":
                if(!mapMetodos.containsValue(param1.getNombre().toLowerCase()) && esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": Debe haber encontrado el/la "+param1.getNombre()+" para poder golpearlo");
                }
                if(!mapVariables.containsValue(param1.getNombre().toLowerCase()) && !esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": El "+param1.getNombre()+" debe existir para poder escalarlo");
                }
                resp = MetodosPalabras.golpear(variable);
                break;
            case "construir":
                if(!mapMetodos.containsValue(param1.getNombre().toLowerCase()) && esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": Debe haber encontrado el/la "+ param1.getNombre()+" para poder construir");
                }
                if(!mapVariables.containsValue(param1.getNombre().toLowerCase()) && !esBasico){
                    throw new Exception("Error en la linea "+this.posicion +": El "+param1.getNombre()+" debe existir para poder construir algo");
                }
                resp = MetodosPalabras.construir(variable);
                break;
            default:
                throw new Exception("Error en la linea "+this.posicion +": .La palabra " + palabraClave.getNombre() + " no corresponde a ninguna acción");
        }
        this.respuesta = resp;
        return resp;
    }
}
