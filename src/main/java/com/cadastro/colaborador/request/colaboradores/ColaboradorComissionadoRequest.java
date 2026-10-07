package com.cadastro.colaborador.request.colaboradores;

import java.math.BigDecimal;

public record ColaboradorComissionadoRequest(String nome, String matricula, BigDecimal salario, BigDecimal valorDasVendas, BigDecimal percentualDeComissao) {
}
