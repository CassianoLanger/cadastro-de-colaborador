package com.cadastro.colaborador.entity;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ColaboradorProducao extends Colaborador{
    @Positive
    private BigDecimal quantidadeProduzida;
    @Positive
    private BigDecimal valorPagoPorUnidade;
}
