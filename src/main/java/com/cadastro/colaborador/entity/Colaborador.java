package com.cadastro.colaborador.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@SuperBuilder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Colaborador {
    private int id;
    @NotBlank
    private String nome;
    @Positive
    private BigDecimal salario;
    private TipoColaborador tipo;
}
