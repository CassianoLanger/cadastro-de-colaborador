package com.cadastro.colaborador.entity.paycheck;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class FolhaDePagamento {

    private int id;

    private String matricula;

    @Positive
    private BigDecimal salario;
}
