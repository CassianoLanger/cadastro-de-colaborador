package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.TipoColaborador;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// IMPROVE (local only): Use ConcurrentHashMap or synchronized access for in-memory thread safety
// IMPROVE (local only): Search by id instead of using index directly; consider Map<id, Object>
@Repository
public class ColaboradorRepository implements RepositoryInterface<Colaborador> {

    private final List<Colaborador> colaboradores = new ArrayList<>();

    public ColaboradorRepository() {
        // IMPROVE (local): Seed data is okay for homework, but load from config/file if needed
        colaboradores.add( new Colaborador(0, "João", new BigDecimal("2780"), TipoColaborador.PADRAO));

    }

    // IMPROVE: Search by id, do not assume id == index
    @Override
    public Colaborador getColaboradorById(int id) {
        return colaboradores.get(id);
    }

    // IMPROVE (local): Check for duplicates; generate id locally (AtomicInteger/max+1)
    @Override
    public Colaborador saveColaborador(Colaborador colaborador) {
        colaboradores.add(colaborador);
        return colaborador;
    }

    @Override
    public List<Colaborador> getAll() {
        return colaboradores;
    }

    // IMPROVE: Find by id instead of using id as index
    @Override
    public Colaborador update(Colaborador colaboradorAtualizado) {
        colaboradores.set(
                colaboradorAtualizado.getId(),
                colaboradorAtualizado
        );

        return colaboradorAtualizado;
    }

    // IMPROVE: Search by id/object properly; do not rely solely on equals
    @Override
    public void delete(Colaborador colaborador) {
        colaboradores.remove(colaborador);
    }
}