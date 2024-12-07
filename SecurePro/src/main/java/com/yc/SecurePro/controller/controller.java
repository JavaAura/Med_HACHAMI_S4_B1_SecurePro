package com.yc.SecurePro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public class controller { 
  
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Web!";
    }
}
