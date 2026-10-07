package com.cadastro.colaborador.controller.colaboradores;

import com.cadastro.colaborador.request.colaboradores.ColaboradorProducaoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorProducaoResponse;
import com.cadastro.colaborador.service.colaboradores.ColaboradorProducaoService;
import com.cadastro.colaborador.swagger.colaboradores.SwaggerColaboradorProducao;
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
@RequestMapping("/v1/colaborador/producao")
@RequiredArgsConstructor
public class ColaboradorProducaoController implements SwaggerColaboradorProducao {

    private final ColaboradorProducaoService colaboradorService;

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorProducaoResponse> getColaborador(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaborador(id));
    }

    @PostMapping
    public ResponseEntity<ColaboradorProducaoResponse> postColaborador(@RequestBody @Valid ColaboradorProducaoRequest colaborador){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.saveColaborador(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorProducaoResponse> putColaborador(@RequestBody @Valid ColaboradorProducaoRequest colaborador,
                                                                      @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(colaborador, id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteColaborador(@PathVariable("id") Long id){
        colaboradorService.deleteColaborador(id);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Long, ColaboradorProducaoResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body((colaboradorService.getAll()));
    }
}
