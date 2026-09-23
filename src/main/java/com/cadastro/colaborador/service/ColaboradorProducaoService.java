package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.entity.TipoColaborador;
import com.cadastro.colaborador.repository.ColaboradorProducaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorProducaoService {

    private final ColaboradorProducaoRepository colaboradorRepository;

    public ColaboradorProducao getColaborador(int id) {
        return colaboradorRepository.getColaboradorById(id);
    }

    public ColaboradorProducao saveColaborador(ColaboradorProducao colaborador) {
        int id = new ServiceUtils<ColaboradorProducao>().newId(getAll());
        colaborador.setId(id);
        return colaboradorRepository.saveColaborador(colaborador);
    }

    public Map<Integer, ColaboradorProducao> getAll(){
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
