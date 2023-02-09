package com.example.demo.util;

import netscape.javascript.JSObject;

import java.util.HashMap;
import java.util.Map;

public class MetodosPalabras {

    //Metodos que permiten guradar variables
    public static Map<String, String> variable(String nombre, String condicion, String valor){
        HashMap<String, String> map = new HashMap<>();
        map.put(nombre, valor);
        return map;
    }

    //Metodos que permiten procesar metodos

    public static String buscar(String objeto){
        String respuesta = "Encontro " + objeto;
        return respuesta;
    }

    public static String cortar (String objeto){
        String respuesta;
        //TODO: Hacer lista de derivados de objetos
        return null;
    }
    //TODO: Terminar metodos de palabras
}
