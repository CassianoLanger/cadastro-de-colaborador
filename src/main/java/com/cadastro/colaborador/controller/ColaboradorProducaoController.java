package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.service.ColaboradorProducaoService;
import com.cadastro.colaborador.swagger.SwaggerColaborador;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/colaborador/producao")
@RequiredArgsConstructor
public class ColaboradorProducaoController implements SwaggerColaborador<ColaboradorProducao> {

    private final ColaboradorProducaoService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorProducao> getColaborador(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<ColaboradorProducao> postColaborador(@RequestBody @Valid ColaboradorProducao colaborador){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorProducao> putColaborador(@RequestBody @Valid ColaboradorProducao colaborador,
                                                      @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    // IMPROVE (local only): Delete by @PathVariable id; local list removal is fragile
    @DeleteMapping
    public void deleteColaborador(@RequestBody @Valid ColaboradorProducao colaborador){
        colaboradorService.deleteColaborador(colaborador);
    }
}
