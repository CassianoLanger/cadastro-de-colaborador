package com.cadastro.colaborador.response.paycheck;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FolhaDePagamentoResponse(String matricula, BigDecimal salario) {
}
