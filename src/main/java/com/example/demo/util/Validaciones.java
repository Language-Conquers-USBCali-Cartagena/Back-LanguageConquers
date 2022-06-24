package com.example.demo.util;

import org.apache.commons.io.FilenameUtils;

import java.util.Date;

public class Validaciones {

    public static boolean isStringLenght(String palabra, int longitud) {
        boolean tam = false;
        if(palabra.length()>longitud) {
            tam = true;
        }else{
            tam = false;
        }
        return tam;
    }
    public static boolean validExtensionImg(String img){
        boolean isValid = false;
        if(FilenameUtils.getExtension(img).equals("png")||
                FilenameUtils.getExtension(img).equals("jpg") ||
                FilenameUtils.getExtension(img).equals("tiff")||
                FilenameUtils.getExtension(img).equals("jpge") ||
                FilenameUtils.getExtension(img).equals("gif") ||
                FilenameUtils.getExtension(img).equals("svg")){
            return isValid = true;
        }else{
            return isValid = false;
        }
    }

    public static int tiempoEntreFechas(Date fecha1, Date fecha2){
        return fecha2.getMonth() - fecha1.getMonth();
    }
}
