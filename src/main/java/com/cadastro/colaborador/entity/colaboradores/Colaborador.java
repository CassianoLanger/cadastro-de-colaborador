package com.cadastro.colaborador.entity.colaboradores;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "colaborador")
@Table(name = "colaborador")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @Positive
    @Column(name = "salario", precision = 19, scale = 2, nullable = false)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_colaborador", nullable = false)
    private TipoColaborador tipoColaborador;
}
