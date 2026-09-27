package com.cadastro.colaborador.request;

import java.math.BigDecimal;

public record ColaboradorComissionadoRequest(String nome, BigDecimal salario, double valorDasVendas, double percentualDeComissao) {
}
