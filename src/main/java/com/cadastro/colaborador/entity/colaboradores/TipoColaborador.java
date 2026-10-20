package com.cadastro.colaborador.entity.colaboradores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoColaborador {
    PADRAO("PAD"),
    COMISSIONADO("COM"),
    PRODUCAO("PRD");

    private final String matricula;
}
