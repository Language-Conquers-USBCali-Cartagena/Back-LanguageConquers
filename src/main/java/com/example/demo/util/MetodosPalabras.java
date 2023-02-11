package com.example.demo.util;

import netscape.javascript.JSObject;

import java.util.HashMap;
import java.util.Map;

public class MetodosPalabras {


    //Metodos que permiten procesar metodos

    public static String buscar(String objeto){
        String respuesta = objeto;
        return respuesta;
    }

    public static String cortar (String objeto){
        String respuesta = derivados(objeto);

        return respuesta;
    }
    public static String escalar (String objeto){
        String respuesta = "cima del " + objeto;
        return respuesta;
    }

    //TODO: Terminar metodos de palabras
    private static String derivados(String objeto){
        String respuesta = "";
        switch (objeto.toLowerCase()){
            case "arbol":
                respuesta = "madera";
                break;
            case "coco":
                respuesta = "Coco palmera";
                break;
            default:
                return "";
            //TODO: Hacer lista de derivados de objetos
        }
        return respuesta;
    }

}
