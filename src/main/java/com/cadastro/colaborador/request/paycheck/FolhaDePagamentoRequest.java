package com.cadastro.colaborador.request.paycheck;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FolhaDePagamentoRequest(int id, String matricula, BigDecimal salarioFinal) {
}
