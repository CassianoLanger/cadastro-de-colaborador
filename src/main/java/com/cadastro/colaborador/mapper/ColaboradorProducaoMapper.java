package com.cadastro.colaborador.mapper;

import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.request.ColaboradorProducaoRequest;
import com.cadastro.colaborador.response.ColaboradorProducaoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColaboradorProducaoMapper {

    public ColaboradorProducaoResponse toResponse(ColaboradorProducao entity) {
        return ColaboradorProducaoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .salario(entity.getSalario())
                .tipo(entity.getTipo())
                .quantidadeProduzida(entity.getQuantidadeProduzida())
                .valorPagoPorUnidade(entity.getValorPagoPorUnidade())
                .build();
    }

    public ColaboradorProducao toEntity(ColaboradorProducaoRequest request) {
        return ColaboradorProducao.builder()
                .nome(request.nome())
                .salario(request.salario())
                .quantidadeProduzida(request.quantidadeProduzida())
                .valorPagoPorUnidade(request.valorPagoPorUnidade())
                .build();
    }
}
