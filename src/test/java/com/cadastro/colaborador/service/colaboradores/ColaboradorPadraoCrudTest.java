package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorResponse;
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
class ColaboradorPadraoCrudTest {

    @Mock private ColaboradorPadraoRepository repository;
    @InjectMocks private ColaboradorPadraoService service;

    private static ColaboradorPadrao padrao(Long id) {
        return ColaboradorPadrao.builder().id(id).nome("Joao").matricula("PAD001")
                .salario(new BigDecimal("2700")).tipoColaborador(TipoColaborador.PADRAO).build();
    }

    // RF004: consulta devolve os dados do colaborador encontrado
    @Test
    void consultaDevolveDadosDoColaborador() {
        when(repository.findById(1L)).thenReturn(Optional.of(padrao(1L)));
        ColaboradorResponse r = service.getColaborador(1L);
        assertEquals(1L, r.id());
        assertEquals("Joao", r.nome());
    }

    // RF005: a listagem usa o ID como chave do mapa
    @Test
    void listagemUsaIdComoChave() {
        when(repository.findAll()).thenReturn(List.of(padrao(1L), padrao(2L)));
        Map<Long, ColaboradorResponse> mapa = service.getAll();
        assertEquals(Set.of(1L, 2L), mapa.keySet());
    }

    // RF005: sem registros, a listagem é um mapa vazio
    @Test
    void listagemVaziaRetornaMapaVazio() {
        when(repository.findAll()).thenReturn(List.of());
        assertTrue(service.getAll().isEmpty());
    }

    // RF006: atualização altera nome, matrícula e salário
    @Test
    void atualizacaoAlteraNomeMatriculaESalario() {
        when(repository.findById(1L)).thenReturn(Optional.of(padrao(1L)));
        when(repository.save(any(ColaboradorPadrao.class))).thenAnswer(inv -> inv.getArgument(0));

        ColaboradorResponse r = service.updateColaborador(
                new ColaboradorRequest("Novo Nome", "PAD009", new BigDecimal("3000")), 1L);

        assertEquals("Novo Nome", r.nome());
        assertEquals("PAD009", r.matricula());
        assertEquals(0, new BigDecimal("3000").compareTo(r.salario()));
    }

    // RF007: remoção de ID existente chama o delete
    @Test
    void remocaoDeIdExistenteChamaDelete() {
        when(repository.existsById(1L)).thenReturn(true);
        service.deleteColaborador(1L);
        verify(repository).deleteById(1L);
    }

    // RN009: o cadastro grava o tipo PADRAO, independentemente do que vier no request
    @Test
    void cadastroPersisteComTipoPadrao() {
        when(repository.save(any(ColaboradorPadrao.class))).thenAnswer(inv -> inv.getArgument(0));
        service.saveColaborador(new ColaboradorRequest("Joao", "PAD001", new BigDecimal("2700")));

        ArgumentCaptor<ColaboradorPadrao> captor = ArgumentCaptor.forClass(ColaboradorPadrao.class);
        verify(repository).save(captor.capture());
        assertEquals(TipoColaborador.PADRAO, captor.getValue().getTipoColaborador());
    }
}
