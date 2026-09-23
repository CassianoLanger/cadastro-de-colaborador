package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.entity.TipoColaborador;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

// IMPROVE (local only): Use ConcurrentHashMap or synchronized access for in-memory thread safety; use Map for id lookup
@Repository
public class ColaboradorProducaoRepository implements RepositoryInterface<ColaboradorProducao> {

    private final Map<Integer, ColaboradorProducao> colaboradores = new HashMap<>();

    public ColaboradorProducaoRepository() {
        mockColaborador();
    }

    // IMPROVE (local): Search by id, not index
    @Override
    public ColaboradorProducao getColaboradorById(int id) {
        return colaboradores.get(id);
    }

    // IMPROVE (local): Check for duplicates; generate id locally
    @Override
    public ColaboradorProducao saveColaborador(ColaboradorProducao colaborador) {
        colaboradores.put(colaborador.getId(), colaborador);
        return colaborador;
    }

    @Override
    public Map<Integer, ColaboradorProducao> getAll() {
        return colaboradores;
    }

    // IMPROVE (local): Find by id instead of index
    @Override
    public ColaboradorProducao update(
            ColaboradorProducao colaboradorAtualizado) {

        colaboradores.put(
                colaboradorAtualizado.getId(),
                colaboradorAtualizado
        );

        return colaboradorAtualizado;
    }

    // IMPROVE (local): Search by object properly; ensure equals/hashCode work
    @Override
    public void delete(ColaboradorProducao colaborador) {
        colaboradores.remove(colaborador.getId());
    }

    void mockColaborador(){
        colaboradores.put(1, ColaboradorProducao.builder()
                .id(0)
                .nome("Carlos")
                .salario(new BigDecimal("4200"))
                .tipo(TipoColaborador.PRODUCAO)
                .quantidadeProduzida(100)
                .valorPagoPorUnidade(42)
                .build());
    }
}