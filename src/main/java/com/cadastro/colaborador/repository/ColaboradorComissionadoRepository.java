package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.entity.TipoColaborador;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// IMPROVE (local only): Use ConcurrentHashMap or synchronized access for in-memory thread safety; use Map for id lookup
@Repository
public class ColaboradorComissionadoRepository
        implements RepositoryInterface<ColaboradorComissionado> {

    private final List<ColaboradorComissionado> colaboradores = new ArrayList<>();

    public ColaboradorComissionadoRepository() {
        colaboradores.add(ColaboradorComissionado.builder()
                        .id(1)
                        .nome("Maria")
                        .salario(new BigDecimal("12333"))
                        .tipo(TipoColaborador.COMISSIONADO)
                        .build());
    }

    // IMPROVE (local): Search by id, not index; use Map if possible
    @Override
    public ColaboradorComissionado getColaboradorById(int id) {
        return colaboradores.get(id);
    }

    // IMPROVE (local): Return saved object; avoid getLast if possible
    @Override
    public ColaboradorComissionado saveColaborador(
            ColaboradorComissionado colaborador) {

        colaboradores.add(colaborador);
        return colaboradores.getLast();
    }

    @Override
    public List<ColaboradorComissionado> getAll() {
        return colaboradores;
    }

    // IMPROVE (local): Find by id instead of index
    @Override
    public ColaboradorComissionado update(
            ColaboradorComissionado colaboradorAtualizado) {

        colaboradores.set(
                colaboradorAtualizado.getId(),
                colaboradorAtualizado
        );

        return colaboradores.get(colaboradorAtualizado.getId());
    }

    // IMPROVE (local): Search by object properly; ensure equals/hashCode work for in-memory list
    @Override
    public void delete(ColaboradorComissionado colaborador) {
        colaboradores.remove(colaborador);
    }
}