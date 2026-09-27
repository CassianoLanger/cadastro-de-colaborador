package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.entity.TipoColaborador;
import com.cadastro.colaborador.mapper.ColaboradorComissionadoMapper;
import com.cadastro.colaborador.request.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.service.ServiceUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ColaboradorComissionadoRepository implements RepositoryInterface<ColaboradorComissionado> {

    private Map<Integer, ColaboradorComissionado> colaboradores = new ConcurrentHashMap<>();

    private final ServiceUtils serviceUtils;

    public ColaboradorComissionadoRepository(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
        mockColaboradores();
    }

    @Override
    public ColaboradorComissionado getById(int id) {
        return colaboradores.get(id);
    }

    @Override
    public ColaboradorComissionado save(ColaboradorComissionado colaborador) {
        int id = serviceUtils.newId(getAll());
        colaborador.setId(id);
        colaboradores.put(id, colaborador);
        return colaboradores.get(id);
    }

    public ColaboradorComissionado saveColaborador(ColaboradorComissionadoRequest colaborador) {
        int id = serviceUtils.newId(getAll());
        colaboradores.put(id, ColaboradorComissionadoMapper.toEntity(colaborador));

        return colaboradores.values()
                .stream()
                .max(Comparator.comparing(ColaboradorComissionado::getId))
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public Map<Integer, ColaboradorComissionado> getAll() {
        return colaboradores;
    }

    @Override
    public ColaboradorComissionado update(ColaboradorComissionado colaboradorAtualizado) {
        colaboradores.put(colaboradorAtualizado.getId(), colaboradorAtualizado);
        return colaboradorAtualizado;
    }

    @Override
    public void delete(int id) {
        colaboradores.remove(id);
    }

    void mockColaboradores() {
        colaboradores.put(1, ColaboradorComissionado.builder()
                .id(1)
                .nome("Maria")
                .salario(new BigDecimal("12333"))
                .tipo(TipoColaborador.COMISSIONADO)
                .valorDasVendas(5000)
                .percentualDeComissao(10)
                .build());
    }
}
