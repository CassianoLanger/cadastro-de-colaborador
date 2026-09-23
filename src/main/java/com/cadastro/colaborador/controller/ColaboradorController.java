package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.service.ColaboradorService;
import jakarta.validation.Valid;
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
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    // IMPROVE: Add @Valid and exception handling for invalid input
    @PostMapping
    public ResponseEntity<Colaborador> postColaborador(@RequestBody @Valid Colaborador colaborador){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    // IMPROVE: Use @PathVariable explicitly and validate id consistency
    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> putColaborador(@RequestBody @Valid Colaborador colaborador,
                                                      @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    // IMPROVE: Delete should use @PathVariable id instead of @RequestBody, and return 204 No Content
    @DeleteMapping
    public void deleteColaborador(@RequestBody @Valid Colaborador colaborador){
        colaboradorService.deleteColaborador(colaborador);
    }
}
