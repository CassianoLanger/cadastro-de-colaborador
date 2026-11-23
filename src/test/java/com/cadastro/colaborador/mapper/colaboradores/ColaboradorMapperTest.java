package com.cadastro.colaborador.mapper.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.request.colaboradores.ColaboradorRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ColaboradorMapperTest {

    // toResponse copia todos os campos da entidade para a resposta
    @Test
    void toResponseCopiaTodosOsCampos() {
        ColaboradorPadrao entidade = ColaboradorPadrao.builder().id(1L).nome("Joao Moraes")
                .matricula("PAD001").salario(new BigDecimal("2700")).tipoColaborador(TipoColaborador.PADRAO).build();

        ColaboradorResponse r = ColaboradorMapper.toResponse(entidade);

        assertEquals(1L, r.id());
        assertEquals("Joao Moraes", r.nome());
        assertEquals("PAD001", r.matricula());
        assertEquals(new BigDecimal("2700"), r.salario());
        assertEquals(TipoColaborador.PADRAO, r.tipo());
    }

    // toEntity copia nome, matrícula e salário do request, e deixa o ID para o banco
    @Test
    void toEntityCopiaCamposDoRequestSemId() {
        ColaboradorPadrao entidade = ColaboradorMapper.toEntity(
                new ColaboradorRequest("Joao Moraes", "PAD001", new BigDecimal("2700")));

        assertNull(entidade.getId());
        assertEquals("Joao Moraes", entidade.getNome());
        assertEquals("PAD001", entidade.getMatricula());
        assertEquals(new BigDecimal("2700"), entidade.getSalario());
    }
}
