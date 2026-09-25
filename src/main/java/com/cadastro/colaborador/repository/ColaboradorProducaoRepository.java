package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.entity.TipoColaborador;
import com.cadastro.colaborador.mapper.ColaboradorProducaoMapper;
import com.cadastro.colaborador.request.ColaboradorProducaoRequest;
import com.cadastro.colaborador.service.ServiceUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ColaboradorProducaoRepository implements RepositoryInterface<ColaboradorProducao> {

    private Map<Integer, ColaboradorProducao> colaboradores = new ConcurrentHashMap<>();

    private final ServiceUtils serviceUtils;

    public ColaboradorProducaoRepository(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
        mockColaborador();
    }

    @Override
    public ColaboradorProducao getById(int id) {
        return colaboradores.get(id);
    }

    @Override
    public ColaboradorProducao save(ColaboradorProducao colaborador) {
        int id = serviceUtils.newId(getAll());
        colaboradores.put(id, colaborador);
        return colaboradores.get(id);
    }

    public ColaboradorProducao saveColaborador(ColaboradorProducaoRequest colaborador) {
        int id = serviceUtils.newId(getAll());
        colaboradores.put(id, ColaboradorProducaoMapper.toEntity(colaborador));

        return colaboradores.values()
                .stream()
                .max(Comparator.comparing(ColaboradorProducao::getId))
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public Map<Integer, ColaboradorProducao> getAll() {
        return colaboradores;
    }

    @Override
    public ColaboradorProducao update(ColaboradorProducao colaboradorAtualizado) {
        colaboradores.put(colaboradorAtualizado.getId(), colaboradorAtualizado);
        return colaboradorAtualizado;
    }

    @Override
    public void delete(int id) {
        colaboradores.remove(id);
    }
    // REV: mock pode gerar conflito de id com saveColaborador; avaliar seed inicial
    void mockColaborador() {
        colaboradores.put(1, ColaboradorProducao.builder()
                .id(1)
                .nome("Carlos")
                .salario(new BigDecimal("4200"))
                .tipo(TipoColaborador.PRODUCAO)
                .quantidadeProduzida(new BigDecimal("100"))
                .valorPagoPorUnidade(new BigDecimal("42"))
                .build());
    }
}
