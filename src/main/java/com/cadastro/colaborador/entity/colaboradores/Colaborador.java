package com.cadastro.colaborador.entity.colaboradores;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "colaborador")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricula", length = 255)
    private String matricula;

    @NotBlank
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Positive
    @Column(name = "salario", precision = 19, scale = 2, nullable = false)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_colaborador", length = 255, nullable = false)
    private TipoColaborador tipoColaborador;
}
