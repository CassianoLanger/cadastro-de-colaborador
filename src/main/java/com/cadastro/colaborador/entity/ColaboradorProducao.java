package com.cadastro.colaborador.entity;


public class ColaboradorProducao extends Colaborador {

    private double quantidadeProduzida;
    private double valorPorUnidade;

    public ColaboradorProducao() {
        super();
    }

    public ColaboradorProducao(int id, String nome, double salario, double quantidadeProduzida, double valorPorUnidade) {
        super(Long.valueOf(id), nome, salario);
        this.quantidadeProduzida = quantidadeProduzida;
        this.valorPorUnidade = valorPorUnidade;
    }

    public Double getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(double quantidadeProduzida) {
        if (quantidadeProduzida < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public Double getValorPorUnidade() {
        return valorPorUnidade;
    }

    public void setValorPorUnidade(double valorPorUnidade) {
        if (valorPorUnidade < 0) {
            throw new IllegalArgumentException("Valor por unidade não pode ser negativo");
        }
        this.valorPorUnidade = valorPorUnidade;
    }

    public Double CalcularSalario() {
        Double produtividade = quantidadeProduzida * valorPorUnidade;
        return getSalario() + produtividade;
    }

    public Double obterAdicionalProdutividade(){
        return quantidadeProduzida * valorPorUnidade;
    }

    public String getColaborador(){
        return "Produção";
    }

    @Override
    public String toString() {
        return "ColaboradorProducao{" +
                "Nome:" + getNome() +
                ",id:" + getId() +
                ",Salario:" + getSalario() +
                ",quantidadeProduzida:" + quantidadeProduzida +
                ", valorPorUnidade:" + valorPorUnidade +
                ", salarioFinal:" + CalcularSalario() +
                '}';
    }
}

