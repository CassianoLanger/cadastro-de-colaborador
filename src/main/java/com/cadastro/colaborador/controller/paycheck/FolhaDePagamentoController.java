package com.cadastro.colaborador.controller.paycheck;

import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoCompletaResponse;
import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoResponse;
import com.cadastro.colaborador.service.paycheck.FolhaDePagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/folha-de-pagamento")
public class FolhaDePagamentoController {

    private final FolhaDePagamentoService folhaDePagamentoService;

    @Operation(summary = "Folha consolidada",
            description = "Retorna a folha de todos os colaboradores, ordenada por ID, com o total a pagar.")
    @GetMapping
    public FolhaDePagamentoCompletaResponse gerarRelatorio() {
        return folhaDePagamentoService.gerarFolha();
    }

    @Operation(summary = "Folha individual",
            description = "Retorna nome, matrícula, tipo, salário base, adicional e salário final de um colaborador de qualquer tipo.")
    @GetMapping("/{colaboradorId}")
    public FolhaDePagamentoResponse gerarFolhaIndividual(@PathVariable Long colaboradorId) {
        return folhaDePagamentoService.gerarFolhaIndividual(colaboradorId);
    }
}
