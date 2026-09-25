package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.request.ColaboradorRequest;
import com.cadastro.colaborador.response.ColaboradorResponse;
import com.cadastro.colaborador.service.ColaboradorService;
import com.cadastro.colaborador.swagger.SwaggerColaborador;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/colaborador")
@RequiredArgsConstructor
public class ColaboradorController implements SwaggerColaborador {

    private final ColaboradorService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> getColaborador(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<ColaboradorResponse> postColaborador(@RequestBody @Valid ColaboradorRequest colaborador){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> putColaborador(@RequestBody @Valid ColaboradorRequest colaborador,
                                                              @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteColaborador(@PathVariable int id){
        colaboradorService.deleteColaborador(id);
    }
}
