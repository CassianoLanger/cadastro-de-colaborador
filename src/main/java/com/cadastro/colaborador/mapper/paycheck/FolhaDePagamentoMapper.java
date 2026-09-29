package com.cadastro.colaborador.mapper.paycheck;

import com.cadastro.colaborador.entity.paycheck.FolhaDePagamento;
import com.cadastro.colaborador.request.paycheck.FolhaDePagamentoRequest;
import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FolhaDePagamentoMapper {

    public FolhaDePagamentoResponse toResponse(FolhaDePagamento entity) {
        return FolhaDePagamentoResponse.builder()
                .matricula(entity.getMatricula())
                .salario(entity.getSalario())
                .build();
    }

    public FolhaDePagamento toEntity(FolhaDePagamentoRequest request) {
        return FolhaDePagamento.builder()
                .id(request.id())
                .matricula(request.matricula())
                .salario(request.salarioFinal())
                .build();
    }
}
