package com.cadastro.colaborador;

import com.cadastro.colaborador.entity.paycheck.FolhaDePagamentoCompleto;

import java.nio.file.Path;

public class GeradorDeFolha {

    public static void FileMaker(FolhaDePagamentoCompleto folhaDePagamentoCompleto){
        Path path = Path.of("Relatorio.txt");

        StringBuilder stringBuilders = new StringBuilder();

        folhaDePagamentoCompleto.getColaboradores();

    }
}
