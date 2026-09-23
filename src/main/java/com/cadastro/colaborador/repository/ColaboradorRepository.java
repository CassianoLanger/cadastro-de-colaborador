package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.TipoColaborador;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// IMPROVE (local only): Use ConcurrentHashMap or synchronized access for in-memory thread safety
// IMPROVE (local only): Search by id instead of using index directly; consider Map<id, Object>
@Repository
public class ColaboradorRepository implements RepositoryInterface<Colaborador> {

    private final Map<Integer,Colaborador> colaboradores = new ConcurrentHashMap<>();

    public ColaboradorRepository() {
        mockColaboradores();
    }
    @Override
    public Colaborador getColaboradorById(int id) {
        return colaboradores.get(id);
    }

    // IMPROVE (local): Check for duplicates; generate id locally (AtomicInteger/max+1)
    @Override
    public Colaborador saveColaborador(Colaborador colaborador) {
        colaboradores.put(colaborador.getId(), colaborador);
        return colaborador;
    }

    @Override
    public Map<Integer, Colaborador> getAll() {
        return colaboradores;
    }

    @Override
    public Colaborador update(Colaborador colaboradorAtualizado) {
        colaboradores.put(colaboradorAtualizado.getId(), colaboradorAtualizado);
        return colaboradorAtualizado;
    }

    // IMPROVE: Search by id/object properly; do not rely solely on equals
    @Override
    public void delete(Colaborador colaborador) {
        colaboradores.remove(colaborador.getId());
    }


    void mockColaboradores(){
        colaboradores.put(1, new Colaborador(1, "João", new BigDecimal("2780"), TipoColaborador.PADRAO));
    }
}