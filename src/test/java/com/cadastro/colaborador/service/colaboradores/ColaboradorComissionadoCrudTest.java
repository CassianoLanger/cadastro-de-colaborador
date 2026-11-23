package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorComissionadoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColaboradorComissionadoCrudTest {

    @Mock private ColaboradorComissionadoRepository repository;
    @InjectMocks private ColaboradorComissionadoService service;

    private static ColaboradorComissionado comissionado(Long id) {
        return ColaboradorComissionado.builder().id(id).nome("Maria").matricula("COM001")
                .salario(new BigDecimal("12333")).tipoColaborador(TipoColaborador.COMISSIONADO)
                .valorDasVendas(new BigDecimal("5000")).percentualDeComissao(new BigDecimal("10"))
                .comissao(new BigDecimal("500.00")).build();
    }

    // RF004: consulta devolve vendas, percentual e comissão
    @Test
    void consultaDevolveDadosDaComissao() {
        when(repository.findById(2L)).thenReturn(Optional.of(comissionado(2L)));
        ColaboradorComissionadoResponse r = service.getColaborador(2L);
        assertEquals(new BigDecimal("500.00"), r.comissao());
        assertEquals(new BigDecimal("5000"), r.valorDasVendas());
    }

    // RF005: a listagem usa o ID como chave do mapa
    @Test
    void listagemUsaIdComoChave() {
        when(repository.findAll()).thenReturn(List.of(comissionado(2L), comissionado(5L)));
        Map<Long, ColaboradorComissionadoResponse> mapa = service.getAll();
        assertEquals(Set.of(2L, 5L), mapa.keySet());
    }

    // RF005: sem registros, a listagem é um mapa vazio
    @Test
    void listagemVaziaRetornaMapaVazio() {
        when(repository.findAll()).thenReturn(List.of());
        assertTrue(service.getAll().isEmpty());
    }

    // RF006 / RN010: atualização recalcula a comissão com os novos valores
    @Test
    void atualizacaoRecalculaComissaoComNovasVendas() {
        when(repository.findById(2L)).thenReturn(Optional.of(comissionado(2L)));
        when(repository.save(any(ColaboradorComissionado.class))).thenAnswer(inv -> inv.getArgument(0));

        ColaboradorComissionadoResponse r = service.updateColaborador(
                new ColaboradorComissionadoRequest("Maria", "COM001", new BigDecimal("12333"),
                        new BigDecimal("2000"), new BigDecimal("15")), 2L);

        assertEquals(new BigDecimal("300.00"), r.comissao());
    }

    // RF007: remoção de ID existente chama o delete
    @Test
    void remocaoDeIdExistenteChamaDelete() {
        when(repository.existsById(2L)).thenReturn(true);
        service.deleteColaborador(2L);
        verify(repository).deleteById(2L);
    }

    // RN009: o cadastro grava o tipo COMISSIONADO
    @Test
    void cadastroPersisteComTipoComissionado() {
        when(repository.save(any(ColaboradorComissionado.class))).thenAnswer(inv -> inv.getArgument(0));
        service.saveColaborador(new ColaboradorComissionadoRequest("Maria", "COM001", new BigDecimal("12333"),
                new BigDecimal("5000"), new BigDecimal("10")));

        ArgumentCaptor<ColaboradorComissionado> captor = ArgumentCaptor.forClass(ColaboradorComissionado.class);
        verify(repository).save(captor.capture());
        assertEquals(TipoColaborador.COMISSIONADO, captor.getValue().getTipoColaborador());
    }
}
