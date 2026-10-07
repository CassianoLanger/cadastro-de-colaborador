package com.cadastro.colaborador.response.paycheck;

import java.math.BigDecimal;
import java.util.List;

public record FolhaDePagamentoCompletaResponse(List<FolhaDePagamentoResponse> colaboradores,
                                               BigDecimal totalFolha) {
}
