package com.example.demo.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static boolean validarCadena(String cadena) {
        return cadena.matches("[a-zA-Z ]+");
    }
    public static boolean validarCadenaConNumeros(String cadena) {
        return cadena.matches("[a-zA-Z0-9]+");
    }

    public static boolean validarFechaNacimiento(Date fechaNacimiento) {
        // Definir la fecha mínima y máxima
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.YEAR, -10); // Restar 10 años
            Date fechaMinima = cal.getTime();
            cal.add(Calendar.YEAR, -73); // Restar 73 años en total (10 más que lo anterior)
            Date fechaMaxima = cal.getTime();

        // Comparar la fecha de nacimiento con la fecha mínima y máxima
        return fechaNacimiento.after(fechaMaxima) && fechaNacimiento.before(fechaMinima);
    }
    public static int tiempoEntreFechas(Date fecha1, Date fecha2){
        return fecha2.getMonth() - fecha1.getMonth();
    }

    public static String validarToken(String idToken) throws FirebaseAuthException, IOException {
        FileInputStream serviceAccountStream = new FileInputStream("C:\\Users\\juanc\\Documents\\PDG\\BACK\\Back-LanguageConquers\\src\\main\\resources\\Credentials");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        FirebaseApp.initializeApp();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseToken decodedToken = auth.verifyIdToken(idToken);
        String uid = decodedToken.getUid();

        return uid;
    }
}
