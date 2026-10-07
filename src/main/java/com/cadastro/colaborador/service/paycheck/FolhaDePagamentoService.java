package com.cadastro.colaborador.service.paycheck;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorProducaoRepository;
import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoCompletaResponse;
import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FolhaDePagamentoService {
    private final ColaboradorPadraoRepository colaboradorPadraoRepository;
    private final ColaboradorComissionadoRepository colaboradorComissionadoRepository;
    private final ColaboradorProducaoRepository colaboradorProducaoRepository;

    public FolhaDePagamentoCompletaResponse gerarFolha() {
        List<FolhaDePagamentoResponse> itens = new ArrayList<>();

        colaboradorPadraoRepository.findAll().stream()
                .map(this::paraFolhaPadrao)
                .forEach(itens::add);
        colaboradorComissionadoRepository.findAll().stream()
                .map(this::paraFolhaComissionado)
                .forEach(itens::add);
        colaboradorProducaoRepository.findAll().stream()
                .map(this::paraFolhaProducao)
                .forEach(itens::add);

        itens.sort(Comparator.comparing(FolhaDePagamentoResponse::colaboradorId));
        BigDecimal total = itens.stream()
                .map(FolhaDePagamentoResponse::salarioFinal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new FolhaDePagamentoCompletaResponse(List.copyOf(itens), total);
    }

    private FolhaDePagamentoResponse paraFolhaPadrao(ColaboradorPadrao colaborador) {
        return paraFolha(colaborador, BigDecimal.ZERO);
    }

    private FolhaDePagamentoResponse paraFolhaComissionado(ColaboradorComissionado colaborador) {
        return paraFolha(colaborador, colaborador.getComissao());
    }

    private FolhaDePagamentoResponse paraFolhaProducao(ColaboradorProducao colaborador) {
        BigDecimal quantidade = Objects.requireNonNullElse(colaborador.getQuantidadeProduzida(), BigDecimal.ZERO);
        BigDecimal valorUnidade = Objects.requireNonNullElse(colaborador.getValorUnidade(), BigDecimal.ZERO);
        return paraFolha(colaborador, quantidade.multiply(valorUnidade));
    }

    private FolhaDePagamentoResponse paraFolha(com.cadastro.colaborador.entity.colaboradores.Colaborador colaborador,
                                                BigDecimal adicional) {
        BigDecimal adicionalSeguro = Objects.requireNonNullElse(adicional, BigDecimal.ZERO);
        return FolhaDePagamentoResponse.builder()
                .colaboradorId(colaborador.getId())
                .matricula(colaborador.getMatricula())
                .nome(colaborador.getNome())
                .tipo(colaborador.getTipoColaborador())
                .salarioBase(colaborador.getSalario())
                .adicional(adicionalSeguro)
                .salarioFinal(colaborador.getSalario().add(adicionalSeguro))
                .build();
    }
}
