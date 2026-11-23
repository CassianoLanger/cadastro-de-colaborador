package com.cadastro.colaborador.service.paycheck;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorProducaoRepository;
import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoCompletaResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FolhaDePagamentoCasosTest {

    @Mock private ColaboradorPadraoRepository padraoRepository;
    @Mock private ColaboradorComissionadoRepository comissionadoRepository;
    @Mock private ColaboradorProducaoRepository producaoRepository;
    @InjectMocks private FolhaDePagamentoService service;

    // CT020: base sem colaboradores -> folha vazia e total zero
    @Test
    void folhaSemColaboradoresTemTotalZeroESemItens() {
        when(padraoRepository.findAll()).thenReturn(List.of());
        when(comissionadoRepository.findAll()).thenReturn(List.of());
        when(producaoRepository.findAll()).thenReturn(List.of());

        FolhaDePagamentoCompletaResponse folha = service.gerarFolha();

        assertTrue(folha.colaboradores().isEmpty());
        assertEquals(0, BigDecimal.ZERO.compareTo(folha.totalFolha()));
    }

    // CT017 (comissionado isolado): adicional é a comissão e salário final é a soma
    @Test
    void comissionadoSomaComissaoAoSalarioBase() {
        ColaboradorComissionado maria = ColaboradorComissionado.builder().id(2L).nome("Maria")
                .matricula("COM001").salario(new BigDecimal("12333")).tipoColaborador(TipoColaborador.COMISSIONADO)
                .valorDasVendas(new BigDecimal("5000")).percentualDeComissao(new BigDecimal("10"))
                .comissao(new BigDecimal("500.00")).build();
        when(comissionadoRepository.findAll()).thenReturn(List.of(maria));

        var item = service.gerarFolha().colaboradores().get(0);

        assertEquals(0, new BigDecimal("500.00").compareTo(item.adicional()));
        assertEquals(0, new BigDecimal("12833.00").compareTo(item.salarioFinal()));
    }

    // RN006 / RN007: padrão tem salário final igual ao salário base
    @Test
    void padraoSalarioFinalEhIgualAoSalarioBase() {
        ColaboradorPadrao joao = ColaboradorPadrao.builder().id(1L).nome("Joao").matricula("PAD001")
                .salario(new BigDecimal("2700")).tipoColaborador(TipoColaborador.PADRAO).build();
        when(padraoRepository.findAll()).thenReturn(List.of(joao));

        var item = service.gerarFolha().colaboradores().get(0);

        assertEquals(0, new BigDecimal("2700").compareTo(item.salarioFinal()));
    }

    // RF008: a folha junta os registros de cada tipo
    @Test
    void folhaIncluiPadraoEComissionadoQuandoOsOutrosEstaoVazios() {
        when(padraoRepository.findAll()).thenReturn(List.of(ColaboradorPadrao.builder().id(1L).nome("Joao")
                .matricula("PAD001").salario(new BigDecimal("2700")).tipoColaborador(TipoColaborador.PADRAO).build()));
        when(comissionadoRepository.findAll()).thenReturn(List.of(ColaboradorComissionado.builder().id(2L)
                .nome("Maria").matricula("COM001").salario(new BigDecimal("12333"))
                .tipoColaborador(TipoColaborador.COMISSIONADO).valorDasVendas(new BigDecimal("5000"))
                .percentualDeComissao(new BigDecimal("10")).comissao(new BigDecimal("500.00")).build()));

        assertEquals(2, service.gerarFolha().colaboradores().size());
    }
}
