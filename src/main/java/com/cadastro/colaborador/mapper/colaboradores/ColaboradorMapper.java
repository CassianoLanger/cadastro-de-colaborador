package com.cadastro.colaborador.mapper.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.Colaborador;
import com.cadastro.colaborador.request.colaboradores.ColaboradorRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColaboradorMapper {
    
    public ColaboradorResponse toResponse(Colaborador entity){
        return ColaboradorResponse.builder()
                .matricula(entity.getMatricula())
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
