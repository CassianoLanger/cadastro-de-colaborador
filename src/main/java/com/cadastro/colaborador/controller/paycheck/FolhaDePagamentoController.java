package com.cadastro.colaborador.controller.paycheck;

import com.cadastro.colaborador.service.paycheck.FolhaDePagamentoService;
import com.cadastro.colaborador.response.paycheck.FolhaDePagamentoCompletaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/folha-de-pagamento")
public class FolhaDePagamentoController {

    private final FolhaDePagamentoService folhaDePagamentoService;

    @GetMapping
    public FolhaDePagamentoCompletaResponse gerarRelatorio(){
        return folhaDePagamentoService.gerarFolha();
    }
}
