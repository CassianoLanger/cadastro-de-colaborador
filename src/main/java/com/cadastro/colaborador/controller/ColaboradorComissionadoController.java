package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.service.ColaboradorComissionadoService;
import com.cadastro.colaborador.swagger.SwaggerColaborador;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/v1/colaborador/comissionado")
@RequiredArgsConstructor
public class ColaboradorComissionadoController implements SwaggerColaborador<ColaboradorComissionado> {

    private final ColaboradorComissionadoService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorComissionado> getColaborador(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<ColaboradorComissionado> postColaborador(@RequestBody @Valid ColaboradorComissionado colaborador){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorComissionado> putColaborador(@RequestBody @Valid ColaboradorComissionado colaborador,
                                                      @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    // IMPROVE (local only): Delete by @PathVariable id, not full object; local list removal is fragile
    @DeleteMapping
    public void deleteColaborador(@RequestBody @Valid ColaboradorComissionado colaborador){
        colaboradorService.deleteColaborador(colaborador);
    }
}
