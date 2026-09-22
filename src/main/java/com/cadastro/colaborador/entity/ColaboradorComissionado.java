package com.cadastro.colaborador.entity;

public class ColaboradorComissionado extends Colaborador {

    private double valorVendas;
    private double percentualComissao;

    public ColaboradorComissionado() {
        super();
    }

    public ColaboradorComissionado(
            int id,
            String nome,
            double salario,
            double valorVendas,
            double percentualComissao) {

        super(Long.valueOf(id), nome, salario);

        setValorVendas(valorVendas);
        setPercentualComissao(percentualComissao);
    }

    public double getValorVendas() {
        return valorVendas;
    }

    public void setValorVendas(double valorVendas) {
        if (valorVendas < 0) {
            throw new IllegalArgumentException(
                    "Valor da venda deve ser positivo"
            );
        }

        this.valorVendas = valorVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        if (percentualComissao < 0) {
            throw new IllegalArgumentException(
                    "Percentual deve ser positivo"
            );
        }

        this.percentualComissao = percentualComissao;
    }

    public double calcularSalario() {
        double comissao = valorVendas * percentualComissao;
        return getSalario() + comissao;
    }
}