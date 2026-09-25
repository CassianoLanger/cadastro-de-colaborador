package com.cadastro.colaborador.mapper;

import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.request.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.ColaboradorComissionadoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColaboradorComissionadoMapper {

    public ColaboradorComissionadoResponse toResponse(ColaboradorComissionado entity) {
        return ColaboradorComissionadoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .salario(entity.getSalario())
                .tipo(entity.getTipo())
                .valorDasVendas(entity.getValorDasVendas())
                .percentualDeComissao(entity.getPercentualDeComissao())
                .build();
    }

    public ColaboradorComissionado toEntity(ColaboradorComissionadoRequest request) {
        return ColaboradorComissionado.builder()
                .nome(request.nome())
                .salario(request.salario())
                .valorDasVendas(request.valorDasVendas())
                .percentualDeComissao(request.percentualDeComissao())
                .build();
    }
}
