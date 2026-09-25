package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.entity.TipoColaborador;
import com.cadastro.colaborador.mapper.ColaboradorComissionadoMapper;
import com.cadastro.colaborador.repository.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.request.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.ColaboradorComissionadoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorComissionadoService {

    private final ColaboradorComissionadoRepository colaboradorRepository;

    // REV: getColaborador pode falhar com NPE; considerar exceção customizada
    public ColaboradorComissionadoResponse getColaborador(int id) {
        return ColaboradorComissionadoMapper.toResponse(colaboradorRepository.getById(id));
    }

    // REV: saveColaborador não valida request; adicionar @Valid e checagens
    public ColaboradorComissionadoResponse saveColaborador(ColaboradorComissionadoRequest colaborador) {
        return ColaboradorComissionadoMapper.toResponse(colaboradorRepository.saveColaborador(colaborador));
    }

    public Map<Integer, ColaboradorComissionadoResponse> getAll() {
        return colaboradorRepository.getAll().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ColaboradorComissionadoMapper.toResponse(entry.getValue())
                ));
    }

    public ColaboradorComissionadoResponse updateColaborador(ColaboradorComissionadoRequest request, int id) {
        ColaboradorComissionadoResponse colaborador = getColaborador(id);
        ColaboradorComissionado colaboradorAtual = ColaboradorComissionadoMapper.toEntity(request);

        if (isNull(colaboradorAtual) || isNull(colaborador)) {
            throw new NullPointerException("Colaborador não encontrado");
        }

        ColaboradorComissionado colaboradorAtualizado = ColaboradorComissionado.builder()
                .id(id)
                .nome(request.nome())
                .salario(request.salario())
                .valorDasVendas(request.valorDasVendas())
                .percentualDeComissao(request.percentualDeComissao())
                .tipo(TipoColaborador.COMISSIONADO)
                .build();

        return ColaboradorComissionadoMapper.toResponse(colaboradorRepository.update(colaboradorAtualizado));
    }

    public void deleteColaborador(int id) {
        colaboradorRepository.delete(id);
    }
}
