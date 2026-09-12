package com.cadastro.colaborador.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Colaborador {
    private Long id;
    private String nome;
    private double salario;
}
