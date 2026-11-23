package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorProducaoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorProducaoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorProducaoResponse;
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
class ColaboradorProducaoCrudTest {

    @Mock private ColaboradorProducaoRepository repository;
    @InjectMocks private ColaboradorProducaoService service;

    private static ColaboradorProducao producao(Long id) {
        return ColaboradorProducao.builder().id(id).nome("Carlos").matricula("PRD001")
                .salario(new BigDecimal("4200")).tipoColaborador(TipoColaborador.PRODUCAO)
                .quantidadeProduzida(new BigDecimal("100")).valorUnidade(new BigDecimal("42")).build();
    }

    // RF004: consulta devolve quantidade e valor por unidade
    @Test
    void consultaDevolveQuantidadeEValorPorUnidade() {
        when(repository.findById(3L)).thenReturn(Optional.of(producao(3L)));
        ColaboradorProducaoResponse r = service.getColaborador(3L);
        assertEquals(0, new BigDecimal("100").compareTo(r.quantidadeProduzida()));
        assertEquals(0, new BigDecimal("42").compareTo(r.valorPagoPorUnidade()));
    }

    // RF005: a listagem usa o ID como chave do mapa
    @Test
    void listagemUsaIdComoChave() {
        when(repository.findAll()).thenReturn(List.of(producao(3L), producao(4L)));
        Map<Long, ColaboradorProducaoResponse> mapa = service.getAll();
        assertEquals(Set.of(3L, 4L), mapa.keySet());
    }

    // RF005: sem registros, a listagem é um mapa vazio
    @Test
    void listagemVaziaRetornaMapaVazio() {
        when(repository.findAll()).thenReturn(List.of());
        assertTrue(service.getAll().isEmpty());
    }

    // RF006: atualização altera quantidade e valor por unidade
    @Test
    void atualizacaoAlteraQuantidadeEValorPorUnidade() {
        when(repository.findById(3L)).thenReturn(Optional.of(producao(3L)));
        when(repository.save(any(ColaboradorProducao.class))).thenAnswer(inv -> inv.getArgument(0));

        ColaboradorProducaoResponse r = service.updateColaborador(
                new ColaboradorProducaoRequest("Carlos", "PRD001", new BigDecimal("4200"),
                        new BigDecimal("50"), new BigDecimal("10")), 3L);

        assertEquals(0, new BigDecimal("50").compareTo(r.quantidadeProduzida()));
        assertEquals(0, new BigDecimal("10").compareTo(r.valorPagoPorUnidade()));
    }

    // RF007: remoção de ID existente chama o delete
    @Test
    void remocaoDeIdExistenteChamaDelete() {
        when(repository.existsById(3L)).thenReturn(true);
        service.deleteColaborador(3L);
        verify(repository).deleteById(3L);
    }

    // RN009: o cadastro grava o tipo PRODUCAO
    @Test
    void cadastroPersisteComTipoProducao() {
        when(repository.save(any(ColaboradorProducao.class))).thenAnswer(inv -> inv.getArgument(0));
        service.saveColaborador(new ColaboradorProducaoRequest("Carlos", "PRD001", new BigDecimal("4200"),
                new BigDecimal("100"), new BigDecimal("42")));

        ArgumentCaptor<ColaboradorProducao> captor = ArgumentCaptor.forClass(ColaboradorProducao.class);
        verify(repository).save(captor.capture());
        assertEquals(TipoColaborador.PRODUCAO, captor.getValue().getTipoColaborador());
    }
}
