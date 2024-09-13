package com.br.magalu.desafio.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageControllerImpl  {

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }
}
