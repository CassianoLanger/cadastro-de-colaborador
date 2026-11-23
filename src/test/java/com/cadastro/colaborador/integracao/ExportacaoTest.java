package com.cadastro.colaborador.integracao;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// CT022 (RF010). Extensão de escopo ainda não implementada (BUG002).
// Opção A: implemente GeradorDeFolha e remova o @Disabled.
// Opção B: retire RF010 do escopo formalmente no PGQ e remova esta classe.
@SpringBootTest
@Disabled("RF010 não implementada (BUG002)")
class ExportacaoTest extends IntegracaoBase {

    @Test
    void ct022_gerarFolhaCriaRelatorioTxt() throws Exception {
        Path relatorio = Path.of("Relatorio.txt");
        Files.deleteIfExists(relatorio);
        mvc.perform(get("/v1/folha-de-pagamento")).andExpect(status().isOk());
        assertTrue(Files.exists(relatorio), "Relatorio.txt não foi criado");
        Files.deleteIfExists(relatorio);
    }
}
