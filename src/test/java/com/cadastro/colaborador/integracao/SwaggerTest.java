package com.cadastro.colaborador.integracao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// CT021 (automatizável). A revisão visual das descrições continua manual.
@SpringBootTest
class SwaggerTest extends IntegracaoBase {

    @Test
    void ct021_swaggerUiEstaDisponivel() throws Exception {
        mvc.perform(get("/swagger-ui/index.html")).andExpect(status().isOk());
    }

    @Test
    void ct021_endpointsDeListagemEFolhaTemDescricao() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/v1/colaborador/all'].get.description").isNotEmpty())
                .andExpect(jsonPath("$.paths['/v1/colaborador/comissionado/all'].get.description").isNotEmpty())
                .andExpect(jsonPath("$.paths['/v1/colaborador/producao/all'].get.description").isNotEmpty())
                .andExpect(jsonPath("$.paths['/v1/folha-de-pagamento'].get.description").isNotEmpty())
                .andExpect(jsonPath("$.paths['/v1/folha-de-pagamento/{colaboradorId}'].get.description").isNotEmpty());
    }

    @Test
    void ct021_codigosDocumentadosSaoOsReais() throws Exception {
        // BUG004: o /all do padrão documentava 204 e 404, mas retorna 200
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/v1/colaborador/all'].get.responses['200']").exists())
                .andExpect(jsonPath("$.paths['/v1/colaborador/all'].get.responses['204']").doesNotExist())
                .andExpect(jsonPath("$.paths['/v1/colaborador/all'].get.responses['404']").doesNotExist());
    }
}
