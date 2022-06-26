package com.example.demo.controller;


import com.example.demo.model.Correo;
import com.example.demo.model.Sms;
import com.example.demo.model.dto.SmsDTO;
import com.example.demo.service.MailSenderService;
import com.sendgrid.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TwilioController {

    @Autowired
    private MailSenderService mailSenderService;

    @Operation(summary = "Este metodo permite enviar mensajes SMS utilizando Twilio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio Exitoso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sms.class)) }),
            @ApiResponse(responseCode = "400", description = "Envio Invalido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sms no encontrado",
                    content = @Content) })
    @PostMapping("/sendmessage")
    public ResponseEntity<Object> sendmessage(@RequestBody SmsDTO smsrequest) {
        try {
            mailSenderService.sendsms(smsrequest);
            return new ResponseEntity<Object>("OK", HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite enviar correos electronicos utilizando Twilio SendGrid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio Exitoso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Correo.class)) }),
            @ApiResponse(responseCode = "400", description = "Envio Invalido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Correo no encontrado",
                    content = @Content) })
    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Correo correoRequest){
       Response response = mailSenderService.envioCorreo(correoRequest);
        if(response.getStatusCode()==200 || response.getStatusCode() == 202){
           return new ResponseEntity<>("Envio exitoso", HttpStatus.OK);
       }else{
            return new ResponseEntity<>("Fallo el envio", HttpStatus.NOT_FOUND);
       }
    }



}
