package com.cadastro.colaborador.service.paycheck;

import java.math.BigDecimal;

public record FolhaDePagamaneto(Integer matricula,
                                String nome,
                                String tipoColaborador,
                                BigDecimal salarioBase,
                                BigDecimal adicional,
                                BigDecimal salarioFinal){
}
