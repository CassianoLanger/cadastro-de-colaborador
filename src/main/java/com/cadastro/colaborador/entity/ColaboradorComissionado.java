package com.cadastro.colaborador.entity;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ColaboradorComissionado extends Colaborador{
    @Positive
    private double valorDasVendas;
    @Positive
    private double percentualDeComissao;
    private BigDecimal comissao;
}
