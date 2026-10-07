package com.cadastro.colaborador.entity.colaboradores;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "colaborador_producao")
public class ColaboradorProducao extends Colaborador {

    @Positive
    @Column(name = "quantidade_produzida", precision = 19, scale = 2)
    private BigDecimal quantidadeProduzida;

    @Positive
    @Column(name = "valor_unidade", precision = 19, scale = 2)
    private BigDecimal valorUnidade;
}
