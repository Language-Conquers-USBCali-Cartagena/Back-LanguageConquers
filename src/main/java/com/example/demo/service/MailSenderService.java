package com.example.demo.service;

import com.example.demo.model.Correo;
import com.example.demo.model.Sms;
import com.example.demo.model.dto.SmsDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailSenderService {

    @Autowired
    SendGrid sendGrid;

    private Sms mapeoSMS(SmsDTO smsDTO){
        Sms sms = new Sms();
        sms.setMessage(smsDTO.getMessage());
        sms.setNumber(smsDTO.getNumber());
        return sms;
    }
    //send message to number
    public void sendsms(SmsDTO smsrequest) {
        Sms sms = mapeoSMS(smsrequest);
        System.out.println(sms.getNumber());
        Twilio.init("ACbdfeee118c112ff1b4050f33f8f6fa6e","5af952b738e747134201be372aa0ab1d");
        Message message=Message.creator(
                new PhoneNumber(sms.getNumber()),
                new PhoneNumber("+18507504768"),
                sms.getMessage()).create();
        //return message.getStatus().toString();
    }

    public Response envioCorreo(Correo email){

        Mail mail = new Mail(new Email("languageconquersusb22@gmail.com"), email.getSubject(),
                new Email(email.getTo()), new Content("text/plain", email.getBody()));
        mail.setReplyTo(new Email("angelamaria731@hotmail.com"));
        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = this.sendGrid.api(request);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }


}
