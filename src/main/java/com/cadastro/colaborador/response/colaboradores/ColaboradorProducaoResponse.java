package com.cadastro.colaborador.response.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ColaboradorProducaoResponse(Long id, String matricula, String nome, BigDecimal salario, TipoColaborador tipo, BigDecimal quantidadeProduzida, BigDecimal valorPagoPorUnidade) {
}
