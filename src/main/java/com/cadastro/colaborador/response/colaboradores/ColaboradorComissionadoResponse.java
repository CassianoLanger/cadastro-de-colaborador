package com.cadastro.colaborador.response.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ColaboradorComissionadoResponse(Long id, String matricula, String nome, BigDecimal salario, TipoColaborador tipo, BigDecimal valorDasVendas, BigDecimal percentualDeComissao, BigDecimal comissao) {
}
