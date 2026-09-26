package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.request.ColaboradorProducaoRequest;
import com.cadastro.colaborador.response.ColaboradorProducaoResponse;
import com.cadastro.colaborador.service.ColaboradorProducaoService;
import com.cadastro.colaborador.swagger.SwaggerColaboradorProducao;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/v1/colaborador/producao")
@RequiredArgsConstructor
public class ColaboradorProducaoController implements SwaggerColaboradorProducao {

    private final ColaboradorProducaoService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorProducaoResponse> getColaborador(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<ColaboradorProducaoResponse> postColaborador(@RequestBody @Valid ColaboradorProducaoRequest colaborador){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorProducaoResponse> putColaborador(@RequestBody @Valid ColaboradorProducaoRequest colaborador,
                                                                      @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteColaborador(@PathVariable int id){
        colaboradorService.deleteColaborador(id);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Integer, ColaboradorProducaoResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body((colaboradorService.getAll()));
    }
}
