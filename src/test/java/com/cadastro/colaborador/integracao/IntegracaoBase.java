package com.cadastro.colaborador.integracao;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class IntegracaoBase {

    protected static final MediaType JSON = MediaType.APPLICATION_JSON;

    protected static final String PADRAO_JSON =
            "{\"nome\":\"Joao Moraes\",\"matricula\":\"PAD001\",\"salario\":2700}";
    protected static final String COMISSIONADO_JSON =
            "{\"nome\":\"Maria\",\"matricula\":\"COM001\",\"salario\":12333,\"valorDasVendas\":5000,\"percentualDeComissao\":10}";
    protected static final String PRODUCAO_JSON =
            "{\"nome\":\"Carlos\",\"matricula\":\"PRD001\",\"salario\":4200,\"quantidadeProduzida\":100,\"valorPagoPorUnidade\":42}";

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    @BeforeEach
    void criaMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    protected static String idDe(String json) {
        Matcher m = Pattern.compile("\"id\":(\\d+)").matcher(json);
        assertTrue(m.find(), "resposta sem id: " + json);
        return m.group(1);
    }
}
