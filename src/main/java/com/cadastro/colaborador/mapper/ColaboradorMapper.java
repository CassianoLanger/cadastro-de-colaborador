package com.cadastro.colaborador.mapper;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.request.ColaboradorRequest;
import com.cadastro.colaborador.response.ColaboradorResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColaboradorMapper {
    
    public ColaboradorResponse toResponse(Colaborador entity){
        return ColaboradorResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .salario(entity.getSalario())
                .tipo(entity.getTipo())
                .build();
    }
    
    public Colaborador toEntity(ColaboradorRequest request){
        return Colaborador.builder()
                .nome(request.nome())
                .salario(request.salario())
                .build();
    }
}
