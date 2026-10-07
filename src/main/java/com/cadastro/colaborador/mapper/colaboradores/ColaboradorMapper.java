package com.cadastro.colaborador.mapper.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.request.colaboradores.ColaboradorRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColaboradorMapper {
    
    public ColaboradorResponse toResponse(ColaboradorPadrao entity){
        return ColaboradorResponse.builder()
                .id(entity.getId())
                .matricula(entity.getMatricula())
                .nome(entity.getNome())
                .salario(entity.getSalario())
                .tipo(entity.getTipoColaborador())
                .build();
    }
    
    public ColaboradorPadrao toEntity(ColaboradorRequest request){
        return ColaboradorPadrao.builder()
                .matricula(request.matricula())
                .nome(request.nome())
                .salario(request.salario())
                .build();
    }
}
