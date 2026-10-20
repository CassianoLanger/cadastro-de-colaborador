package com.cadastro.colaborador.response.paycheck;

import lombok.Builder;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;

import java.math.BigDecimal;

@Builder
public record FolhaDePagamentoResponse(Long colaboradorId,
                                       String matricula,
                                       String nome,
                                       TipoColaborador tipo,
                                       BigDecimal salarioBase,
                                       BigDecimal adicional,
                                       BigDecimal salarioFinal) {
}
