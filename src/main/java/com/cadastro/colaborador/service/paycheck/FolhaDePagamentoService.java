package com.cadastro.colaborador.service.paycheck;

import com.cadastro.colaborador.entity.paycheck.FolhaDePagamento;
import com.cadastro.colaborador.entity.paycheck.FolhaDePagamentoCompleto;
import com.cadastro.colaborador.mapper.paycheck.FolhaDePagamentoMapper;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorProducaoRepository;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.repository.paycheck.FolhaDePagamantoRepository;
import com.cadastro.colaborador.request.paycheck.FolhaDePagamentoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FolhaDePagamentoService {

    private final FolhaDePagamantoRepository repository;
    private final ColaboradorPadraoRepository colaboradorPadraoRepository;
    private final ColaboradorProducaoRepository colaboradorProducaoRepository;
    private final ColaboradorComissionadoRepository colaboradorComissionadoRepository;

    public FolhaDePagamentoCompleto gerarFolhaDePagamentoCompleto() {

        return FolhaDePagamentoCompleto.builder()
                .colaboradores(colaboradorPadraoRepository.getAll())
                .colaboradoresProducao(colaboradorProducaoRepository.getAll())
                .colaboradoresComissionados(colaboradorComissionadoRepository.getAll())
                .folhaDePagamento(getFolhaDePagamento())
                .build();
    }

    private Map<Integer, FolhaDePagamento> getFolhaDePagamento(){
        return repository.getAll();
    }

    public void salvarFolha(FolhaDePagamentoRequest dadosFolha){

        repository.save(FolhaDePagamentoMapper.toEntity(dadosFolha));
    }
}
