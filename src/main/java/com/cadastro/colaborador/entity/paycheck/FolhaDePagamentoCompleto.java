package com.cadastro.colaborador.entity.paycheck;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
public class FolhaDePagamentoCompleto {
    private Map<Integer, ColaboradorPadrao> colaboradores;
    private Map<Integer, ColaboradorComissionado> colaboradoresComissionados;
    private Map<Integer, ColaboradorProducao> colaboradoresProducao;
    private Map<Integer,FolhaDePagamento> folhaDePagamento;
}
