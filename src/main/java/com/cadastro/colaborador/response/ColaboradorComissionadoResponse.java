package com.cadastro.colaborador.response;

import com.cadastro.colaborador.entity.TipoColaborador;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ColaboradorComissionadoResponse(int id, String nome, BigDecimal salario, TipoColaborador tipo, double valorDasVendas, double percentualDeComissao) {
}
