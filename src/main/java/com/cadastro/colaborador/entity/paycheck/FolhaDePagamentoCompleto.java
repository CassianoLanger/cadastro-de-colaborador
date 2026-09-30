package com.cadastro.colaborador.entity.paycheck;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FolhaDePagamentoCompleto {
    private Map<Integer, ColaboradorPadrao> colaboradores;
    private Map<Integer, ColaboradorComissionado> colaboradoresComissionados;
    private Map<Integer, ColaboradorProducao> colaboradoresProducao;
    private Map<Integer,FolhaDePagamento> folhaDePagamento;
}
