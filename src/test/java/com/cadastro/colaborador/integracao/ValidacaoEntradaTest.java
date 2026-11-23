package com.cadastro.colaborador.integracao;

import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorProducaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

// CT002, CT003, CT004, CT007, CT026, CT027, CT030, CT031
@SpringBootTest
@Transactional
class ValidacaoEntradaTest extends IntegracaoBase {

    @Autowired private ColaboradorPadraoRepository padraoRepository;
    @Autowired private ColaboradorComissionadoRepository comissionadoRepository;
    @Autowired private ColaboradorProducaoRepository producaoRepository;

    private long totalGravados() {
        return padraoRepository.count() + comissionadoRepository.count() + producaoRepository.count();
    }

    private void esperaRejeicao(String url, String json, String campo) throws Exception {
        long antes = totalGravados();
        mvc.perform(post(url).contentType(JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(campo)));
        assertEquals(antes, totalGravados(), "a entrada inválida não pode ser gravada");
    }

    @Test
    void ct002_salarioNegativoEhRejeitadoSemGravar() throws Exception {
        esperaRejeicao("/v1/colaborador",
                "{\"nome\":\"Teste\",\"matricula\":\"PAD002\",\"salario\":-100}", "salario");
    }

    @Test
    void ct003_nomeVazioEhRejeitadoSemGravar() throws Exception {
        esperaRejeicao("/v1/colaborador",
                "{\"nome\":\"\",\"matricula\":\"PAD003\",\"salario\":2000}", "nome");
    }

    @Test
    void ct004_salarioAusenteEhRejeitadoSemGravar() throws Exception {
        esperaRejeicao("/v1/colaborador",
                "{\"nome\":\"Teste\",\"matricula\":\"PAD004\"}", "salario");
    }

    @Test
    void ct007_comissionadoSemValorDeVendasEhRejeitado() throws Exception {
        esperaRejeicao("/v1/colaborador/comissionado",
                "{\"nome\":\"Teste\",\"matricula\":\"COM003\",\"salario\":3000,\"percentualDeComissao\":10}",
                "valorDasVendas");
    }

    @Test
    void ct026_vendasNegativasSaoRejeitadas() throws Exception {
        esperaRejeicao("/v1/colaborador/comissionado",
                "{\"nome\":\"Vendas Negativas\",\"matricula\":\"COM006\",\"salario\":2000,\"valorDasVendas\":-100,\"percentualDeComissao\":10}",
                "valorDasVendas");
    }

    @Test
    void ct027_percentualNegativoEhRejeitado() throws Exception {
        esperaRejeicao("/v1/colaborador/comissionado",
                "{\"nome\":\"Percentual Negativo\",\"matricula\":\"COM007\",\"salario\":2000,\"valorDasVendas\":5000,\"percentualDeComissao\":-5}",
                "percentualDeComissao");
    }

    @Test
    void ct030_quantidadeNegativaEhRejeitada() throws Exception {
        esperaRejeicao("/v1/colaborador/producao",
                "{\"nome\":\"Quantidade Negativa\",\"matricula\":\"PRD005\",\"salario\":2500,\"quantidadeProduzida\":-1,\"valorPagoPorUnidade\":10}",
                "quantidadeProduzida");
    }

    @Test
    void ct031_valorPorUnidadeNegativoEhRejeitado() throws Exception {
        esperaRejeicao("/v1/colaborador/producao",
                "{\"nome\":\"Valor Negativo\",\"matricula\":\"PRD006\",\"salario\":2500,\"quantidadeProduzida\":10,\"valorPagoPorUnidade\":-10}",
                "valorPagoPorUnidade");
    }
}
