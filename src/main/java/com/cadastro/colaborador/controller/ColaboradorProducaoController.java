package com.cadastro.colaborador.controller;

import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.repository.ColaboradorProducaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/colaborador/producao")
@RequiredArgsConstructor
public class ColaboradorProducaoController {

    private final ColaboradorProducaoRepository colaboradorProducaoRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ColaboradorProducao colaborador){
        try {
            ColaboradorProducao salvo = colaboradorProducaoRepository.salvar(colaborador);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorProducao>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorProducaoRepository.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorProducao> buscarPorId(@PathVariable("id") Long id){
        return colaboradorProducaoRepository.buscarPorId(id)
                .map(c -> ResponseEntity.status(HttpStatus.OK).body(c))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorProducao> atualizar(@PathVariable("id") Long id,
                                                         @RequestBody ColaboradorProducao colaborador){
        return colaboradorProducaoRepository.atualizar(id, colaborador)
                .map(c -> ResponseEntity.status(HttpStatus.OK).body(c))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ColaboradorProducao> deletar(@PathVariable("id") Long id){
        boolean deletado = colaboradorProducaoRepository.deletar(id);
        if(!deletado){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
