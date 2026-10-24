package com.portfolio.comercial.controller;

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Olá! Minha primeira API java está funcionando";
    }
    
}       
