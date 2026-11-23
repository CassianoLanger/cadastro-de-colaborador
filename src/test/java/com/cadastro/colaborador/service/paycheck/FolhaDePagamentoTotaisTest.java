package com.cadastro.colaborador.service.paycheck;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorProducaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FolhaDePagamentoTotaisTest {

    @Mock private ColaboradorPadraoRepository padraoRepository;
    @Mock private ColaboradorComissionadoRepository comissionadoRepository;
    @Mock private ColaboradorProducaoRepository producaoRepository;

    @InjectMocks
    private FolhaDePagamentoService service;

    private void mockarTresTipos() {
        when(padraoRepository.findAll()).thenReturn(List.of(ColaboradorPadrao.builder().id(1L).nome("Joao")
                .matricula("PAD001").salario(new BigDecimal("2700")).tipoColaborador(TipoColaborador.PADRAO).build()));
        when(comissionadoRepository.findAll()).thenReturn(List.of(ColaboradorComissionado.builder().id(2L)
                .nome("Maria").matricula("COM001").salario(new BigDecimal("12333"))
                .tipoColaborador(TipoColaborador.COMISSIONADO).valorDasVendas(new BigDecimal("5000"))
                .percentualDeComissao(new BigDecimal("10")).comissao(new BigDecimal("500.00")).build()));
        when(producaoRepository.findAll()).thenReturn(List.of(ColaboradorProducao.builder().id(3L)
                .nome("Carlos").matricula("PRD001").salario(new BigDecimal("4200"))
                .tipoColaborador(TipoColaborador.PRODUCAO).quantidadeProduzida(new BigDecimal("100"))
                .valorUnidade(new BigDecimal("42")).build()));
    }

    // RF008 / RN008: total = soma dos salários finais (2700 + 12833 + 8400)
    @Test
    void totalDaFolhaEhSomaDosSalariosFinais() {
        mockarTresTipos();
        assertEquals(0, new BigDecimal("23933").compareTo(service.gerarFolha().totalFolha()));
    }

    // RF008 / RN008: itens ordenados por ID
    @Test
    void itensSaoOrdenadosPorId() {
        mockarTresTipos();
        assertEquals(List.of(1L, 2L, 3L),
                service.gerarFolha().colaboradores().stream().map(i -> i.colaboradorId()).toList());
    }

    // RN005 / BUG003: adicional de produção sai com 2 casas decimais
    @Test
    void adicionalDeProducaoTemDuasCasasDecimais() {
        mockarTresTipos();
        var item = service.gerarFolha().colaboradores().get(2);
        assertEquals(2, item.adicional().scale());
    }
}
