package com.cadastro.colaborador.mapper.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import com.cadastro.colaborador.request.colaboradores.ColaboradorProducaoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorProducaoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColaboradorProducaoMapper {

    public ColaboradorProducaoResponse toResponse(ColaboradorProducao entity) {
        return ColaboradorProducaoResponse.builder()
                .matricula(entity.getMatricula())
                .nome(entity.getNome())
                .salario(entity.getSalario())
                .tipo(entity.getTipo())
                .quantidadeProduzida(entity.getQuantidadeProduzida())
                .valorPagoPorUnidade(entity.getValorUnidade())
                .build();
    }

    public ColaboradorProducao toEntity(ColaboradorProducaoRequest request) {
        return ColaboradorProducao.builder()
                .nome(request.nome())
                .salario(request.salario())
                .quantidadeProduzida(request.quantidadeProduzida())
                .valorUnidade(request.valorPagoPorUnidade())
                .build();
    }
}
