package com.cadastro.colaborador.response.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ColaboradorResponse(Long id, String matricula, String nome, BigDecimal salario, TipoColaborador tipo) {
}
