package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/colaborador")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> getColaborador(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }
}
