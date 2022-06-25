package com.example.demo.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

    public static boolean isStringLenght(String palabra, int longitud) {
        return palabra.length() > longitud;
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
            return false;
        }
    }

    public static boolean formatoCorreoValido(String correo) {
        boolean res = false;
        Pattern pat = Pattern.compile("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$");
        Matcher mat = pat.matcher(correo);
        if(mat.find()) {
            res = true;
        }
        return res;
    }

    public static boolean urlValidator(String url) {
        // Obteniendo UrlValidator
        UrlValidator defaultValidator = new UrlValidator();
        return defaultValidator.isValid(url);
    }

    public static int tiempoEntreFechas(Date fecha1, Date fecha2){
        return fecha2.getMonth() - fecha1.getMonth();
    }
}
