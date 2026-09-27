package com.cadastro.colaborador.mapper.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.request.colaboradores.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorComissionadoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColaboradorComissionadoMapper {

    public ColaboradorComissionadoResponse toResponse(ColaboradorComissionado entity) {
        return ColaboradorComissionadoResponse.builder()
                .matricula(entity.getMatricula())
                .nome(entity.getNome())
                .salario(entity.getSalario())
                .tipo(entity.getTipo())
                .valorDasVendas(entity.getValorDasVendas())
                .percentualDeComissao(entity.getPercentualDeComissao())
                .comissao(entity.getComissao())
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
