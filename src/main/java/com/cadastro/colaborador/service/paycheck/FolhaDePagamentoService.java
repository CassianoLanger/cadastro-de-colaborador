package com.cadastro.colaborador.service.paycheck;

import com.cadastro.colaborador.entity.colaboradores.Colaborador;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import com.cadastro.colaborador.exception.NotFoundException;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorProducaoRepository;
import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoCompletaResponse;
import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FolhaDePagamentoService {

    private static final BigDecimal ZERO = new BigDecimal("0.00");

    private final ColaboradorPadraoRepository colaboradorPadraoRepository;
    private final ColaboradorComissionadoRepository colaboradorComissionadoRepository;
    private final ColaboradorProducaoRepository colaboradorProducaoRepository;

    public FolhaDePagamentoCompletaResponse gerarFolha() {
        List<FolhaDePagamentoResponse> itens = new ArrayList<>();

        colaboradorPadraoRepository.findAll().stream()
                .map(c -> paraFolha(c, ZERO))
                .forEach(itens::add);
        colaboradorComissionadoRepository.findAll().stream()
                .map(c -> paraFolha(c, c.getComissao()))
                .forEach(itens::add);
        colaboradorProducaoRepository.findAll().stream()
                .map(c -> paraFolha(c, adicionalProducao(c)))
                .forEach(itens::add);

        itens.sort(Comparator.comparing(FolhaDePagamentoResponse::colaboradorId));
        BigDecimal total = itens.stream()
                .map(FolhaDePagamentoResponse::salarioFinal)
                .reduce(ZERO, BigDecimal::add);

        return new FolhaDePagamentoCompletaResponse(List.copyOf(itens), total);
    }

    // RF011: procura o ID nos três tipos; a rota não diz o tipo
    public FolhaDePagamentoResponse gerarFolhaIndividual(Long id) {
        Optional<? extends Colaborador> encontrado = colaboradorPadraoRepository.findById(id);
        if (encontrado.isEmpty()) {
            encontrado = colaboradorComissionadoRepository.findById(id);
        }
        if (encontrado.isEmpty()) {
            encontrado = colaboradorProducaoRepository.findById(id);
        }
        Colaborador colaborador = encontrado
                .orElseThrow(() -> new NotFoundException("Colaborador não encontrado para id: " + id));
        return paraFolha(colaborador, adicionalDe(colaborador));
    }

    private BigDecimal adicionalDe(Colaborador colaborador) {
        if (colaborador instanceof ColaboradorComissionado comissionado) {
            return Objects.requireNonNullElse(comissionado.getComissao(), ZERO);
        }
        if (colaborador instanceof ColaboradorProducao producao) {
            return adicionalProducao(producao);
        }
        return ZERO;
    }

    // RN005 / BUG003: quantidade x valor, arredondado em 2 casas
    private BigDecimal adicionalProducao(ColaboradorProducao producao) {
        BigDecimal quantidade = Objects.requireNonNullElse(producao.getQuantidadeProduzida(), BigDecimal.ZERO);
        BigDecimal valorUnidade = Objects.requireNonNullElse(producao.getValorUnidade(), BigDecimal.ZERO);
        return quantidade.multiply(valorUnidade).setScale(2, RoundingMode.HALF_UP);
    }

    private FolhaDePagamentoResponse paraFolha(Colaborador colaborador, BigDecimal adicional) {
        BigDecimal adicionalSeguro = Objects.requireNonNullElse(adicional, ZERO);
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
