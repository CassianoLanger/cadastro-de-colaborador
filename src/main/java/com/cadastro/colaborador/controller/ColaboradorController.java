package com.cadastro.colaborador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/colaborador")
public class ColaboradorController {

    @GetMapping
    public String getColaborador(){
        return "colaborador";
    }
}
