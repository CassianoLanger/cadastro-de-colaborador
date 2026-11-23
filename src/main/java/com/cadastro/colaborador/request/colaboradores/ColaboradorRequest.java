package com.cadastro.colaborador.request.colaboradores;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ColaboradorRequest(
        @NotBlank(message = "é obrigatório") String nome,
        String matricula,
        @NotNull(message = "é obrigatório") @PositiveOrZero(message = "não pode ser negativo") BigDecimal salario) {
}
