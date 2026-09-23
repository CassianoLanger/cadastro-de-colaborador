package com.cadastro.colaborador.entity;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

// IMPROVE (local): Add @NoArgsConstructor for SuperBuilder; do not use DB entities
@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ColaboradorProducao extends Colaborador{
    @Positive
    private double quantidadeProduzida;
    @Positive
    private double valorPagoPorUnidade;
}
