package com.cadastro.colaborador.integracao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Zero é permitido pelo cliente (RN001, RN004, RN013). Hoje o BUG009 recusa com 500.
@SpringBootTest
@Transactional
class ZeroAceitoIntegracaoTest extends IntegracaoBase {

    // CT023: salário zero devolve 201
    @Test
    void ct023_salarioZeroEhAceito() throws Exception {
        mvc.perform(post("/v1/colaborador").contentType(JSON)
                        .content("{\"nome\":\"Salario Zero\",\"matricula\":\"PAD006\",\"salario\":0}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"salario\":0")));
    }

    // CT024: vendas zero devolve 201 com comissão 0.00
    @Test
    void ct024_vendasZeroEhAceitaComComissaoZero() throws Exception {
        mvc.perform(post("/v1/colaborador/comissionado").contentType(JSON)
                        .content("{\"nome\":\"Vendas Zero\",\"matricula\":\"COM004\",\"salario\":2000,\"valorDasVendas\":0,\"percentualDeComissao\":10}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"comissao\":0.00")));
    }

    // CT025: percentual zero devolve 201 com comissão 0.00
    @Test
    void ct025_percentualZeroEhAceitoComComissaoZero() throws Exception {
        mvc.perform(post("/v1/colaborador/comissionado").contentType(JSON)
                        .content("{\"nome\":\"Percentual Zero\",\"matricula\":\"COM005\",\"salario\":2000,\"valorDasVendas\":5000,\"percentualDeComissao\":0}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"comissao\":0.00")));
    }

    // CT028: quantidade zero devolve 201 e adicional zero na folha
    @Test
    void ct028_quantidadeZeroEhAceitaComAdicionalZero() throws Exception {
        mvc.perform(post("/v1/colaborador/producao").contentType(JSON)
                        .content("{\"nome\":\"Quantidade Zero\",\"matricula\":\"PRD003\",\"salario\":2500,\"quantidadeProduzida\":0,\"valorPagoPorUnidade\":10}"))
                .andExpect(status().isCreated());
        mvc.perform(get("/v1/folha-de-pagamento"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.colaboradores[?(@.matricula=='PRD003')].adicional").value(hasItem(0.0)));
    }

    // CT029: valor por unidade zero devolve 201 e adicional zero na folha
    @Test
    void ct029_valorPorUnidadeZeroEhAceitoComAdicionalZero() throws Exception {
        mvc.perform(post("/v1/colaborador/producao").contentType(JSON)
                        .content("{\"nome\":\"Valor Zero\",\"matricula\":\"PRD004\",\"salario\":2500,\"quantidadeProduzida\":50,\"valorPagoPorUnidade\":0}"))
                .andExpect(status().isCreated());
        mvc.perform(get("/v1/folha-de-pagamento"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.colaboradores[?(@.matricula=='PRD004')].adicional").value(hasItem(0.0)));
    }
}
