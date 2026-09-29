package com.cadastro.colaborador.repository.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.repository.RepositoryInterface;
import com.cadastro.colaborador.service.ServiceUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ColaboradorPadraoRepository implements RepositoryInterface<ColaboradorPadrao> {

    private final Map<Integer,ColaboradorPadrao> colaboradores = new ConcurrentHashMap<>();

    private final ServiceUtils<ColaboradorPadrao> serviceUtils;

    public ColaboradorPadraoRepository(ServiceUtils<ColaboradorPadrao> serviceUtils) {
        this.serviceUtils = serviceUtils;
        mockColaboradores();
    }

    @Override
    public ColaboradorPadrao getById(int id) {
        return colaboradores.get(id);
    }

    @Override
    public ColaboradorPadrao save(ColaboradorPadrao colaborador) {
        int id = serviceUtils.newId(getAll());
        colaborador.setId(id);
        colaboradores.put(id, colaborador);

        return colaboradores.get(id);
    }

    @Override
    public Map<Integer, ColaboradorPadrao> getAll() {
        return colaboradores;
    }

    @Override
    public ColaboradorPadrao update(ColaboradorPadrao colaboradorAtualizado){
        colaboradores.put(colaboradorAtualizado.getId(), colaboradorAtualizado);
        return colaboradorAtualizado;
    }

    @Override
    public void delete(int id){
        colaboradores.remove(id);
    }


    void mockColaboradores() {
        ColaboradorPadrao colaboradorPadrao = ColaboradorPadrao.builder()
                .id(1)
                .matricula("JM1")
                .nome("Joao Moraes")
                .salario(new BigDecimal("2700"))
                .tipo(TipoColaborador.PADRAO)
                .build();
        colaboradores.put(1,colaboradorPadrao);
    }
}