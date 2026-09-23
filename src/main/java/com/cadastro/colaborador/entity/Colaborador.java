package com.cadastro.colaborador.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class Colaborador {
    private int id;
    private String nome;
    private double salario;
    private TipoColaborador tipo;
}
