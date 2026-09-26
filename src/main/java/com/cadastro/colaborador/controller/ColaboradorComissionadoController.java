package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.request.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.ColaboradorComissionadoResponse;
import com.cadastro.colaborador.service.ColaboradorComissionadoService;
import com.cadastro.colaborador.swagger.SwaggerColaboradorComissionado;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/colaborador/comissionado")
@RequiredArgsConstructor
public class ColaboradorComissionadoController implements SwaggerColaboradorComissionado {

    private final ColaboradorComissionadoService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorComissionadoResponse> getColaborador(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<ColaboradorComissionadoResponse> postColaborador(@RequestBody @Valid ColaboradorComissionadoRequest colaborador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorComissionadoResponse> putColaborador(@RequestBody @Valid ColaboradorComissionadoRequest colaborador,
                                                                          @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteColaborador(@PathVariable int id) {
        colaboradorService.deleteColaborador(id);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Integer, ColaboradorComissionadoResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body((colaboradorService.getAll()));
    }
}
