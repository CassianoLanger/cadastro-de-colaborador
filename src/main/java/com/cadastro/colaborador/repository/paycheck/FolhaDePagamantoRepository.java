package com.cadastro.colaborador.repository.paycheck;

import com.cadastro.colaborador.entity.paycheck.FolhaDePagamento;
import com.cadastro.colaborador.repository.RepositoryInterface;
import com.cadastro.colaborador.service.ServiceUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

@Repository
public class FolhaDePagamantoRepository implements RepositoryInterface<FolhaDePagamento> {

    private final Map<Integer, FolhaDePagamento> folhasDePagamento;
    private final ServiceUtils<FolhaDePagamento> serviceUtils;

    public FolhaDePagamantoRepository(Map<Integer, FolhaDePagamento> folhasDePagamento, ServiceUtils<FolhaDePagamento> serviceUtils) {
        this.folhasDePagamento = folhasDePagamento;
        this.serviceUtils = serviceUtils;
        mockColaboradores();
    }

    @Override
    public FolhaDePagamento getById(int id) {
        return null;
    }

    @Override
    public FolhaDePagamento save(FolhaDePagamento folhaDePagamento) {
        int id = serviceUtils.newId(folhasDePagamento);
        folhasDePagamento.put(id, folhaDePagamento);
        return folhasDePagamento.get(id);
    }

    @Override
    public Map<Integer, FolhaDePagamento> getAll() {
        return folhasDePagamento;
    }

    @Override
    public FolhaDePagamento update(FolhaDePagamento folhaDePagamento) {
        return null;
    }

    @Override
    public void delete(int t) {

    }

    void mockColaboradores() {
        folhasDePagamento.put(1, new FolhaDePagamento(1,"JM1", BigDecimal.valueOf(2700)));
    }
}
