package com.cadastro.colaborador.request.colaboradores;

import java.math.BigDecimal;

public record ColaboradorProducaoRequest(String nome, String matricula, BigDecimal salario, BigDecimal quantidadeProduzida,
                                         BigDecimal valorPagoPorUnidade) {
}
