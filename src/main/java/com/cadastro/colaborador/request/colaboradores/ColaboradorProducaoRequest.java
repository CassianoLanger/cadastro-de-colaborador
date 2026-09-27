package com.cadastro.colaborador.request.colaboradores;

import java.math.BigDecimal;

public record ColaboradorProducaoRequest(String nome, BigDecimal salario, BigDecimal quantidadeProduzida,
                                         BigDecimal valorPagoPorUnidade) {
}
