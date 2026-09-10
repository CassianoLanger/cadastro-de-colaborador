package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/colaborador")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> getColaborador(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.FOUND).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<Colaborador> postColaborador(@RequestBody Colaborador colaborador){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> putColaborador(@RequestBody Colaborador colaborador,
                                                      @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }
}
