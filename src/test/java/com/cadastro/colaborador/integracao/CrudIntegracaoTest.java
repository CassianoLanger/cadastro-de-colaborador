package com.cadastro.colaborador.integracao;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Status HTTP e corpo dos casos CRUD que os testes unitários não verificam
@SpringBootTest
@Transactional
class CrudIntegracaoTest extends IntegracaoBase {

    @Autowired
    private EntityManager em;

    private void leDoBanco() {
        em.flush();
        em.clear();
    }

    // CT001: cadastro padrão devolve 201 e tipo PADRAO
    @Test
    void ct001_cadastroPadraoRetorna201ComTipoPadrao() throws Exception {
        mvc.perform(post("/v1/colaborador").contentType(JSON).content(PADRAO_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"tipo\":\"PADRAO\"")));
    }

    // CT005: cadastro comissionado devolve 201 e comissão de 500.00
    @Test
    void ct005_cadastroComissionadoRetorna201ComComissao() throws Exception {
        mvc.perform(post("/v1/colaborador/comissionado").contentType(JSON).content(COMISSIONADO_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"comissao\":500.00")));
    }

    // CT008: cadastro de produção devolve 201 com tipo PRODUCAO
    @Test
    void ct008_cadastroProducaoRetorna201ComTipoProducao() throws Exception {
        mvc.perform(post("/v1/colaborador/producao").contentType(JSON).content(PRODUCAO_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"tipo\":\"PRODUCAO\"")));
    }

    // CT012: listagem por tipo devolve 200 com o ID como chave
    @Test
    void ct012_listagemDoTipoRetorna200ComIdComoChave() throws Exception {
        String resposta = mvc.perform(post("/v1/colaborador").contentType(JSON).content(PADRAO_JSON))
                .andReturn().getResponse().getContentAsString();
        leDoBanco();
        mvc.perform(get("/v1/colaborador/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"" + idDe(resposta) + "\":")));
    }

    // CT013: atualização do comissionado devolve 200 com comissão recalculada
    @Test
    void ct013_atualizacaoComissionadoRetorna200ComComissaoRecalculada() throws Exception {
        String resposta = mvc.perform(post("/v1/colaborador/comissionado").contentType(JSON).content(COMISSIONADO_JSON))
                .andReturn().getResponse().getContentAsString();
        leDoBanco();
        mvc.perform(put("/v1/colaborador/comissionado/" + idDe(resposta)).contentType(JSON)
                        .content("{\"nome\":\"Maria\",\"matricula\":\"COM001\",\"salario\":12333,\"valorDasVendas\":8000,\"percentualDeComissao\":10}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"comissao\":800.00")));
    }

    // CT014: atualização de ID inexistente devolve 404
    @Test
    void ct014_atualizacaoDeIdInexistenteRetorna404() throws Exception {
        mvc.perform(put("/v1/colaborador/producao/999").contentType(JSON).content(PRODUCAO_JSON))
                .andExpect(status().isNotFound());
    }

    // CT015: remoção devolve 204 e a consulta posterior devolve 404
    @Test
    void ct015_remocaoRetorna204EDepoisConsultaRetorna404() throws Exception {
        String resposta = mvc.perform(post("/v1/colaborador").contentType(JSON)
                        .content("{\"nome\":\"Auxiliar\",\"matricula\":\"PAD005\",\"salario\":1500}"))
                .andReturn().getResponse().getContentAsString();
        String id = idDe(resposta);
        leDoBanco();
        mvc.perform(delete("/v1/colaborador/" + id)).andExpect(status().isNoContent());
        leDoBanco();
        mvc.perform(get("/v1/colaborador/" + id)).andExpect(status().isNotFound());
    }

    // CT016: remoção de ID inexistente devolve 404
    @Test
    void ct016_remocaoDeIdInexistenteRetorna404() throws Exception {
        mvc.perform(delete("/v1/colaborador/comissionado/999"))
                .andExpect(status().isNotFound());
    }
}
