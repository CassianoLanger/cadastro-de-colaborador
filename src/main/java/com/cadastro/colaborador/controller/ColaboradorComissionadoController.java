package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.service.ColaboradorComissionadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/v1/colaborador/comissionado")
@RequiredArgsConstructor
public class ColaboradorComissionadoController {

    private final ColaboradorComissionadoService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> getColaborador(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<Colaborador> postColaborador(@RequestBody @Valid ColaboradorComissionado colaborador){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> putColaborador(@RequestBody @Valid ColaboradorComissionado colaborador,
                                                      @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    // IMPROVE (local only): Delete by @PathVariable id, not full object; local list removal is fragile
    @DeleteMapping
    public void deleteColaborador(@RequestBody @Valid ColaboradorComissionado colaborador){
        colaboradorService.deleteColaborador(colaborador);
    }
}
