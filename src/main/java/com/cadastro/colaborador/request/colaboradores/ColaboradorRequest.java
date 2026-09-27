package com.cadastro.colaborador.request.colaboradores;

import java.math.BigDecimal;

public record ColaboradorRequest(String nome,String matricula, BigDecimal salario) {
}
