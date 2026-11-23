package com.cadastro.colaborador.integracao;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// CT009 (ida ao banco), CT011, CT032
@SpringBootTest
@Transactional
class ConsultaIntegracaoTest extends IntegracaoBase {

    @Autowired
    private EntityManager em;

    // flush + clear força a leitura do banco, e não do cache da sessão
    private void leDoBanco() {
        em.flush();
        em.clear();
    }

    // Cobre a ida ao banco. O reinício da aplicação do CT009 continua manual.
    @Test
    void ct009_dadosGravadosSaoLidosDoBanco() throws Exception {
        String resposta = mvc.perform(post("/v1/colaborador").contentType(JSON).content(PADRAO_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        leDoBanco();
        mvc.perform(get("/v1/colaborador/" + idDe(resposta)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Joao Moraes")));
    }

    @Test
    void ct011_idDeOutroTipoRetorna404() throws Exception {
        String resposta = mvc.perform(post("/v1/colaborador/comissionado").contentType(JSON).content(COMISSIONADO_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        leDoBanco();
        mvc.perform(get("/v1/colaborador/" + idDe(resposta)))
                .andExpect(status().isNotFound());
    }

    @Test
    void ct032_folhaIndividualDeCadaTipo() throws Exception {
        String padrao = mvc.perform(post("/v1/colaborador").contentType(JSON).content(PADRAO_JSON))
                .andReturn().getResponse().getContentAsString();
        String comissionado = mvc.perform(post("/v1/colaborador/comissionado").contentType(JSON).content(COMISSIONADO_JSON))
                .andReturn().getResponse().getContentAsString();
        String producao = mvc.perform(post("/v1/colaborador/producao").contentType(JSON).content(PRODUCAO_JSON))
                .andReturn().getResponse().getContentAsString();
        leDoBanco();

        mvc.perform(get("/v1/folha-de-pagamento/" + idDe(padrao)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"adicional\":0.00")));
        mvc.perform(get("/v1/folha-de-pagamento/" + idDe(comissionado)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"salarioFinal\":12833.00")));
        mvc.perform(get("/v1/folha-de-pagamento/" + idDe(producao)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"salarioFinal\":8400.00")));
    }

    @Test
    void ct032_folhaIndividualDeIdInexistenteRetorna404() throws Exception {
        mvc.perform(get("/v1/folha-de-pagamento/999"))
                .andExpect(status().isNotFound());
    }
}
