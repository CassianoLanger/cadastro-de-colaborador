package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.exception.NotFoundException;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorComissionadoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColaboradorComissionadoServiceTest {

    @Mock
    private ColaboradorComissionadoRepository repository;

    @InjectMocks
    private ColaboradorComissionadoService service;

    @BeforeEach
    void salvaDevolvendoOMesmoObjeto() {
        lenient().when(repository.save(any(ColaboradorComissionado.class)))
                .thenAnswer(inv -> inv.getArgument(0));
    }

    private ColaboradorComissionadoRequest request(String vendas, String percentual) {
        return new ColaboradorComissionadoRequest("Maria", "COM001", new BigDecimal("12333"),
                new BigDecimal(vendas), new BigDecimal(percentual));
    }

    @Test
    void comissaoDeVendas5000Com10PorCentoEh500() {
        assertEquals(new BigDecimal("500.00"), service.saveColaborador(request("5000", "10")).comissao());
    }

    @Test
    void comissaoArredondaHalfUpEm2Casas() {
        // 333,33 x 3,33 / 100 = 11,099889 -> 11,10
        assertEquals(new BigDecimal("11.10"), service.saveColaborador(request("333.33", "3.33")).comissao());
    }

    @Test
    void vendasZeroResultaEmComissaoZero() {
        assertEquals(new BigDecimal("0.00"), service.saveColaborador(request("0", "10")).comissao());
    }

    @Test
    void percentualZeroResultaEmComissaoZero() {
        assertEquals(new BigDecimal("0.00"), service.saveColaborador(request("5000", "0")).comissao());
    }

    @Test
    void atualizacaoRecalculaComissao() {
        ColaboradorComissionado existente = ColaboradorComissionado.builder()
                .id(1L).nome("Maria").matricula("COM001").salario(new BigDecimal("12333"))
                .tipoColaborador(TipoColaborador.COMISSIONADO)
                .valorDasVendas(new BigDecimal("5000")).percentualDeComissao(new BigDecimal("10"))
                .comissao(new BigDecimal("500.00")).build();
        when(repository.findById(1L)).thenReturn(Optional.of(existente));

        assertEquals(new BigDecimal("800.00"), service.updateColaborador(request("8000", "10"), 1L).comissao());
    }

    @Test
    void atualizacaoDeIdInexistenteLancaNotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> service.updateColaborador(request("8000", "10"), 999L));
        assertTrue(ex.getMessage().contains("999"));
    }

    @Test
    void remocaoDeIdInexistenteLancaNotFoundSemRemoverNada() {
        when(repository.existsById(999L)).thenReturn(false);
        NotFoundException ex = assertThrows(NotFoundException.class, () -> service.deleteColaborador(999L));
        assertTrue(ex.getMessage().contains("999"));
        verify(repository, never()).deleteById(anyLong());
    }
}
