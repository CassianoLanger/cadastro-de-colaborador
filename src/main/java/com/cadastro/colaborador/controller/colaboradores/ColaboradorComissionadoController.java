package com.cadastro.colaborador.controller.colaboradores;

import com.cadastro.colaborador.request.colaboradores.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorComissionadoResponse;
import com.cadastro.colaborador.service.colaboradores.ColaboradorComissionadoService;
import com.cadastro.colaborador.swagger.colaboradores.SwaggerColaboradorComissionado;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/colaborador/comissionado")
@RequiredArgsConstructor
public class ColaboradorComissionadoController implements SwaggerColaboradorComissionado {

    private final ColaboradorComissionadoService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorComissionadoResponse> getColaborador(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<ColaboradorComissionadoResponse> postColaborador(@RequestBody @Valid ColaboradorComissionadoRequest colaborador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorComissionadoResponse> putColaborador(@RequestBody @Valid ColaboradorComissionadoRequest colaborador,
                                                                          @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteColaborador(@PathVariable("id") Long id) {
        colaboradorService.deleteColaborador(id);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Long, ColaboradorComissionadoResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body((colaboradorService.getAll()));
    }
}
