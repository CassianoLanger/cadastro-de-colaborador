package com.cadastro.colaborador.mapper.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.request.colaboradores.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorComissionadoResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ColaboradorComissionadoMapperTest {

    // toResponse inclui vendas, percentual e comissão calculada
    @Test
    void toResponseCopiaVendasPercentualEComissao() {
        ColaboradorComissionado entidade = ColaboradorComissionado.builder().id(2L).nome("Maria")
                .matricula("COM001").salario(new BigDecimal("12333")).tipoColaborador(TipoColaborador.COMISSIONADO)
                .valorDasVendas(new BigDecimal("5000")).percentualDeComissao(new BigDecimal("10"))
                .comissao(new BigDecimal("500.00")).build();

        ColaboradorComissionadoResponse r = ColaboradorComissionadoMapper.toResponse(entidade);

        assertEquals(2L, r.id());
        assertEquals(new BigDecimal("5000"), r.valorDasVendas());
        assertEquals(new BigDecimal("10"), r.percentualDeComissao());
        assertEquals(new BigDecimal("500.00"), r.comissao());
        assertEquals(TipoColaborador.COMISSIONADO, r.tipo());
    }

    // toEntity copia os dados do request; a comissão só é calculada no service
    @Test
    void toEntityNaoDefineComissaoAntesDoCalculo() {
        ColaboradorComissionado entidade = ColaboradorComissionadoMapper.toEntity(
                new ColaboradorComissionadoRequest("Maria", "COM001", new BigDecimal("12333"),
                        new BigDecimal("5000"), new BigDecimal("10")));

        assertEquals("Maria", entidade.getNome());
        assertEquals(new BigDecimal("5000"), entidade.getValorDasVendas());
        assertEquals(new BigDecimal("10"), entidade.getPercentualDeComissao());
        assertNull(entidade.getComissao());
    }
}
