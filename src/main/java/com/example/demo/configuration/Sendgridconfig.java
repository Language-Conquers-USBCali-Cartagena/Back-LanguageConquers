package com.example.demo.configuration;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Sendgridconfig {

    @Value("${sendgrid.key}")
    private String key;

    @Bean
    public SendGrid getSendegrid(){
        return new SendGrid(key);
    }

}
