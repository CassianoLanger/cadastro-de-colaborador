package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.ColaboradorProducao;
import com.cadastro.colaborador.entity.TipoColaborador;
import com.cadastro.colaborador.mapper.ColaboradorProducaoMapper;
import com.cadastro.colaborador.repository.ColaboradorProducaoRepository;
import com.cadastro.colaborador.request.ColaboradorProducaoRequest;
import com.cadastro.colaborador.response.ColaboradorProducaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorProducaoService {

    private final ColaboradorProducaoRepository colaboradorRepository;

    // REV: getColaborador pode falhar com NPE se id inexistente; considerar tratamento
    public ColaboradorProducaoResponse getColaborador(int id) {
        return ColaboradorProducaoMapper.toResponse(colaboradorRepository.getById(id));
    }

    // REV: saveColaborador não valida campos obrigatórios do request; adicionar validação
    public ColaboradorProducaoResponse saveColaborador(ColaboradorProducaoRequest colaborador) {
        return ColaboradorProducaoMapper.toResponse(colaboradorRepository.saveColaborador(colaborador));
    }

    // REV: getAll carrega toda dataset em memória; avaliar paginacao
    public Map<Integer, ColaboradorProducaoResponse> getAll() {
        return colaboradorRepository.getAll().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ColaboradorProducaoMapper.toResponse(entry.getValue())
                ));
    }

    // REV: updateColaborador usa NullPointerException; ideal NotFoundException
    // REV: tipo é forçado a PRODUCAO; se necessário preservar outro tipo, ajustar
    public ColaboradorProducaoResponse updateColaborador(ColaboradorProducaoRequest request, int id) {
        ColaboradorProducaoResponse colaborador = getColaborador(id);
        ColaboradorProducao colaboradorAtual = ColaboradorProducaoMapper.toEntity(request);

        if (isNull(colaboradorAtual) || isNull(colaborador)) {
            throw new NullPointerException("Colaborador não encontrado");
        }

        ColaboradorProducao colaboradorAtualizado = ColaboradorProducao.builder()
                .id(id)
                .nome(request.nome())
                .salario(request.salario())
                .quantidadeProduzida(request.quantidadeProduzida())
                .valorPagoPorUnidade(request.valorPagoPorUnidade())
                .tipo(TipoColaborador.PRODUCAO)
                .build();

        return ColaboradorProducaoMapper.toResponse(colaboradorRepository.update(colaboradorAtualizado));
    }

    public void deleteColaborador(int id) {
        colaboradorRepository.delete(id);
    }
}
