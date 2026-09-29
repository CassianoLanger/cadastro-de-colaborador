package com.cadastro.colaborador.controller.paycheck;

import com.cadastro.colaborador.service.paycheck.FolhaDePagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/folha-de-pagamento")
public class FolhaDePagamentoController {

    private final FolhaDePagamentoService folhaDePagamentoService;

    @GetMapping
    public void gerarRelatorio(){
        folhaDePagamentoService.gerarFolhaDePagamentoCompleto();
    }
}
