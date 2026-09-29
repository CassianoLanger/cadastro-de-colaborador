package com.cadastro.colaborador.entity.colaboradores;

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
public abstract class Colaborador {

    protected int id;

    @NotBlank
    protected String matricula;

    @NotBlank
    protected String nome;

    @Positive
    protected BigDecimal salario;
    protected TipoColaborador tipo;
}
