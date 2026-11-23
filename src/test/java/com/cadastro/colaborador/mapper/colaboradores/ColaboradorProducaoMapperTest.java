package com.cadastro.colaborador.mapper.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.request.colaboradores.ColaboradorProducaoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorProducaoResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ColaboradorProducaoMapperTest {

    // toResponse expõe valorUnidade da entidade como valorPagoPorUnidade na resposta
    @Test
    void toResponseMapeiaValorUnidadeParaValorPagoPorUnidade() {
        ColaboradorProducao entidade = ColaboradorProducao.builder().id(3L).nome("Carlos")
                .matricula("PRD001").salario(new BigDecimal("4200")).tipoColaborador(TipoColaborador.PRODUCAO)
                .quantidadeProduzida(new BigDecimal("100")).valorUnidade(new BigDecimal("42")).build();

        ColaboradorProducaoResponse r = ColaboradorProducaoMapper.toResponse(entidade);

        assertEquals(new BigDecimal("100"), r.quantidadeProduzida());
        assertEquals(new BigDecimal("42"), r.valorPagoPorUnidade());
        assertEquals(TipoColaborador.PRODUCAO, r.tipo());
    }

    // toEntity leva valorPagoPorUnidade do request para valorUnidade da entidade
    @Test
    void toEntityMapeiaValorPagoPorUnidadeParaValorUnidade() {
        ColaboradorProducao entidade = ColaboradorProducaoMapper.toEntity(
                new ColaboradorProducaoRequest("Carlos", "PRD001", new BigDecimal("4200"),
                        new BigDecimal("100"), new BigDecimal("42")));

        assertEquals(new BigDecimal("100"), entidade.getQuantidadeProduzida());
        assertEquals(new BigDecimal("42"), entidade.getValorUnidade());
    }
}
