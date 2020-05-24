package com.example.demo.controller;

import com.example.demo.service.SageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {

    @Autowired
    private SageService sageService;

    @GetMapping("/callback")
    public String hello(@RequestParam(required = false) String code, @RequestParam(required = false) String country) {
        System.out.println(code);
        System.out.println(country);

//        sageService.getToken(code);
        sageService.getAccessToken(code);


        return "Hello World";
    }
}
