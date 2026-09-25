package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.TipoColaborador;
import com.cadastro.colaborador.mapper.ColaboradorMapper;
import com.cadastro.colaborador.request.ColaboradorRequest;
import com.cadastro.colaborador.service.ServiceUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ColaboradorRepository implements RepositoryInterface<Colaborador> {

    private Map<Integer,Colaborador> colaboradores = new ConcurrentHashMap<>();

    private final ServiceUtils serviceUtils;

    public ColaboradorRepository(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
        mockColaboradores();
    }

    @Override
    public Colaborador getById(int id) {
        return colaboradores.get(id);
    }

    @Override
    public Colaborador save(Colaborador colaborador) {
        int id = serviceUtils.newId(getAll());
        colaboradores.put(id, colaborador);

        return colaboradores.get(id);
    }

    // IMPROVE (local): Check for duplicates; generate id locally (AtomicInteger/max+1)
    public Colaborador saveColaborador(ColaboradorRequest colaborador) {
        int id = serviceUtils.newId(getAll());

        colaboradores.put(id, ColaboradorMapper.toEntity(colaborador));

        return colaboradores.values()
                .stream()
                .max(Comparator.comparing(Colaborador::getId))
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public Map<Integer, Colaborador> getAll() {
        return colaboradores;
    }

    @Override
    public Colaborador update(Colaborador colaboradorAtualizado){
        colaboradores.put(colaboradorAtualizado.getId(), colaboradorAtualizado);
        return colaboradorAtualizado;
    }

    @Override
    public void delete(int id){
        colaboradores.remove(id);
    }


    void mockColaboradores() {
        colaboradores.put(1, new Colaborador(1, "João", new BigDecimal("2780"), TipoColaborador.PADRAO));
    }
}