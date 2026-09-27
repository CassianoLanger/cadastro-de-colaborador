package com.cadastro.colaborador.response.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ColaboradorComissionadoResponse(String matricula, String nome, BigDecimal salario, TipoColaborador tipo, double valorDasVendas, double percentualDeComissao, BigDecimal comissao) {
}
