package com.cadastro.colaborador.mapper.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.request.colaboradores.ColaboradorRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColaboradorMapper {
    
    public ColaboradorResponse toResponse(ColaboradorPadrao entity){
        return ColaboradorResponse.builder()
                .matricula(entity.getMatricula())
                .nome(entity.getNome())
                .salario(entity.getSalario())
                .tipo(entity.getTipo())
                .build();
    }
    
    public ColaboradorPadrao toEntity(ColaboradorRequest request){
        return ColaboradorPadrao.builder()
                .nome(request.nome())
                .salario(request.salario())
                .build();
    }
}
