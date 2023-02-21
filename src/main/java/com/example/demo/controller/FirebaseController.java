package com.example.demo.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/firebase")
public class FirebaseController {

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");
        try {

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return new ResponseEntity<>(decodedToken, HttpStatus.OK);
        } catch (FirebaseAuthException e) {
            return new ResponseEntity("Invalid token", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}