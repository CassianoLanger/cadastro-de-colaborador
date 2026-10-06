package com.cadastro.colaborador.entity.colaboradores;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Entity(name = "colaborador_comissionado")
@Table(name = "colaborador_comissionado")
public class ColaboradorComissionado extends Colaborador {

    @Positive
    @Column(name = "valor_das_vendas", precision = 19, scale = 2, nullable = false)
    private BigDecimal valorDasVendas;

    @Positive
    @Column(name = "percentual_de_comissao", precision = 19, scale = 2, nullable = false)
    private BigDecimal percentualDeComissao;

    @Column(name = "comissao", precision = 19, scale = 2, nullable = false)
    private BigDecimal comissao;
}
