package com.example.demo.util;

import netscape.javascript.JSObject;

import java.util.HashMap;
import java.util.Map;

public class MetodosPalabras {


    //Metodos que permiten procesar metodos

    public static String buscar(String objeto){
        String respuesta = objeto.toLowerCase();
        return respuesta;
    }

    public static String cortar (String objeto){
        String respuesta = derivados(objeto).toLowerCase();

        return respuesta;
    }
    public static String escalar (String objeto){
        String respuesta = "cima del " + objeto;
        return respuesta.toLowerCase();
    }
    public static String golpear (String objeto){
        String respuesta = "";
        if(objeto.equalsIgnoreCase("rocas")){
            respuesta = "chispas";
        }
        return respuesta.toLowerCase();
    }
    public static String juntar (String objeto1, String objeto2) throws Exception{
        if(objeto1.contains("fuego") && objeto2.equalsIgnoreCase("madera")) {
            return "fogata";
        }else if(objeto1.equalsIgnoreCase("ramas") && objeto2.equalsIgnoreCase("hojas de palma")){
            return "piso choza";
        }
        else{
            throw  new Exception("No se pueden juntar los objetos");
        }
    }
    public static String encender (String objeto, String medio) throws Exception{
        if(!medio.equalsIgnoreCase("chispas")){
            throw new Exception("Necesita chispas para encender el fuego");
        }
        String respuesta =objeto + " con fuego";
        return respuesta.toLowerCase();

    }
    public static String construir (String material1, String material2) throws Exception{
        String resp = "";
        switch(material1.toLowerCase()) {
            case "hojas secas con fuego":
                if(material2.equalsIgnoreCase("madera")) {
                    resp = "fogata";
                } else {
                    throw new Exception("No se pueden juntar los objetos");
                }
                break;
            case "ramas":
                if(material2.equalsIgnoreCase("hojas de palma")) {
                    resp = "piso choza";
                } else {
                    throw new Exception("No se pueden juntar los objetos");
                }
                break;
            case "hojas de palma":
                if(material2.equalsIgnoreCase("ramas")) {
                    resp = "piso choza";
                } else {
                    throw new Exception("No se pueden juntar los objetos");
                }
                break;
            case "piso choza":
                if(material2.equalsIgnoreCase("paredes")){
                    resp = "base choza";
                }else{
                    throw new Exception("No se pueden juntar los objetos");
                }
                break;
            case "paredes":
                if(material2.equalsIgnoreCase("piso choza")){
                    resp = "base choza";
                }else {
                    throw new Exception("No se pueden juntar los objetos");
                }
                break;
            case "base choza":
                if(material2.equalsIgnoreCase("techo")){
                    resp = "choza";
                }else {
                    throw new Exception("No se pueden juntar los objetos");
                }
                break;
            case "techo":
                if(material2.equalsIgnoreCase("base choza")){
                    resp = "choza";
                }else {
                    throw new Exception("No se pueden juntar los objetos");
                }
                break;
            case "piso":
                if(material2.equalsIgnoreCase("pared")){
                    resp = "base choza";
                }else {
                    throw new Exception("No se pueden juntar los objetos");
                }
                break;
            case "pared":
                if(material2.equalsIgnoreCase("piso")){
                    resp = "base choza";
                }else{
                    throw new Exception("No se pueden juntar los objetos");
                }
            default:
                throw new Exception("No se pueden juntar los objetos");
        }
        return resp.toLowerCase();
    }

    public static String construir (String material) throws Exception{
        String resp = "";
        switch (material.toLowerCase()){
            case "madera":
                resp = "paredes";
                break;
            case "hojas de palma":
                resp = "techo";
                break;
            default:
                throw new Exception("No se pueden juntar los objetos");
        }
        return resp;
    }
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
        }
        return respuesta;
    }

}
