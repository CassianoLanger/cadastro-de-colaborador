package com.cadastro.colaborador.entity.colaboradores;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ColaboradorComissionado extends Colaborador {
    @Positive
    private double valorDasVendas;
    @Positive
    private double percentualDeComissao;
    private BigDecimal comissao;
}
