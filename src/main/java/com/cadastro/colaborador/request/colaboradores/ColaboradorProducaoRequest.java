package com.cadastro.colaborador.request.colaboradores;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

// quantidade e valor por unidade ficam opcionais até a decisão do cliente (RN014)
public record ColaboradorProducaoRequest(
        @NotBlank(message = "é obrigatório") String nome,
        String matricula,
        @NotNull(message = "é obrigatório") @PositiveOrZero(message = "não pode ser negativo") BigDecimal salario,
        @PositiveOrZero(message = "não pode ser negativo") BigDecimal quantidadeProduzida,
        @PositiveOrZero(message = "não pode ser negativo") BigDecimal valorPagoPorUnidade) {
}
