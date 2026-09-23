package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.entity.TipoColaborador;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// IMPROVE (local only): Use ConcurrentHashMap or synchronized access for in-memory thread safety; use Map for id lookup
@Repository
public class ColaboradorProducaoRepository implements RepositoryInterface<ColaboradorProducao> {

    private final List<ColaboradorProducao> colaboradores = new ArrayList<>();

    public ColaboradorProducaoRepository() {
        colaboradores.add(
                ColaboradorProducao.builder()
                        .id(0)
                        .nome("Carlos")
                        .salario(new BigDecimal("4200"))
                        .tipo(TipoColaborador.PRODUCAO)
                        .quantidadeProduzida(100)
                        .valorPagoPorUnidade(42)
                        .build()
        );
    }

    // IMPROVE (local): Search by id, not index
    @Override
    public ColaboradorProducao getColaboradorById(int id) {
        return colaboradores.get(id);
    }

    // IMPROVE (local): Check for duplicates; generate id locally
    @Override
    public ColaboradorProducao saveColaborador(ColaboradorProducao colaborador) {
        colaboradores.add(colaborador);
        return colaborador;
    }

    @Override
    public List<ColaboradorProducao> getAll() {
        return colaboradores;
    }

    // IMPROVE (local): Find by id instead of index
    @Override
    public ColaboradorProducao update(
            ColaboradorProducao colaboradorAtualizado) {

        colaboradores.set(
                colaboradorAtualizado.getId(),
                colaboradorAtualizado
        );

        return colaboradorAtualizado;
    }

    // IMPROVE (local): Search by object properly; ensure equals/hashCode work
    @Override
    public void delete(ColaboradorProducao colaborador) {
        colaboradores.remove(colaborador);
    }
}