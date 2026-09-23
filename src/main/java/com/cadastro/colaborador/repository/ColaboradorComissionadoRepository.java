package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.entity.TipoColaborador;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ColaboradorComissionadoRepository
        implements RepositoryInterface<ColaboradorComissionado> {

    private final Map<Integer, ColaboradorComissionado> colaboradores = new HashMap<>();

    public ColaboradorComissionadoRepository() {
        mockColaboradores();
    }

    @Override
    public ColaboradorComissionado getColaboradorById(int id) {
        return colaboradores.get(id);
    }

    @Override
    public ColaboradorComissionado saveColaborador(
            ColaboradorComissionado colaborador) {

        colaboradores.put(colaborador.getId(), colaborador);
        return colaboradores.get(colaborador.getId());
    }

    @Override
    public Map<Integer, ColaboradorComissionado> getAll() {
        return colaboradores;
    }

    @Override
    public ColaboradorComissionado update(ColaboradorComissionado colaboradorAtualizado) {

        colaboradores.put(colaboradorAtualizado.getId(), colaboradorAtualizado);

        return colaboradores.get(colaboradorAtualizado.getId());
    }

    // IMPROVE (local): Search by object properly; ensure equals/hashCode work for in-memory list
    @Override
    public void delete(ColaboradorComissionado colaborador) {
        colaboradores.remove(colaborador.getId());
    }

    void mockColaboradores(){
        colaboradores.put(1, ColaboradorComissionado.builder()
                .id(1)
                .nome("Maria")
                .salario(new BigDecimal("12333"))
                .tipo(TipoColaborador.COMISSIONADO)
                .build());
    }
}