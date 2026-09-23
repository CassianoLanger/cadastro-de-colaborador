package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.entity.TipoColaborador;
import com.cadastro.colaborador.repository.ColaboradorProducaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

// IMPROVE (local only): In-memory service; ensure id consistency and preserve fields locally
@Service
@RequiredArgsConstructor
public class ColaboradorProducaoService {

    private final ColaboradorProducaoRepository colaboradorRepository;

    // IMPROVE: Avoid duplicating logic; use inheritance or composition
    public ColaboradorProducao getColaborador(int id) {
        return colaboradorRepository.getColaboradorById(id);
    }

    public ColaboradorProducao saveColaborador(ColaboradorProducao colaborador) {
        int id = getAll().getLast().getId() + 1;
        colaborador.setId(id);
        return colaboradorRepository.saveColaborador(colaborador);
    }

    public List<ColaboradorProducao> getAll(){
        return colaboradorRepository.getAll();
    }

    public ColaboradorProducao updateColaborador(ColaboradorProducao colaborador, int id) {
        Colaborador colaboradorAtual =  getColaborador(id);

        if(isNull(colaboradorAtual)){
            throw new NullPointerException("Colaborador não encontrado");
        }

        ColaboradorProducao colaboradorAtualizado = ColaboradorProducao.builder()
                .id(id)
                .nome(colaborador.getNome())
                .salario(colaborador.getSalario())
                .quantidadeProduzida(colaborador.getQuantidadeProduzida())
                .valorPagoPorUnidade(colaborador.getValorPagoPorUnidade())
                .tipo(TipoColaborador.PRODUCAO)
                .build();

        return colaboradorRepository.update(colaboradorAtualizado);
    }

    public void deleteColaborador(ColaboradorProducao colaborador) {
        colaboradorRepository.delete(colaborador);
    }
}
