package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.exception.NotFoundException;
import com.cadastro.colaborador.mapper.colaboradores.ColaboradorProducaoMapper;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorProducaoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorProducaoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorProducaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorProducaoService {

    private final ColaboradorProducaoRepository colaboradorRepository;

    public ColaboradorProducaoResponse getColaborador(int id) {
        ColaboradorProducao entity = colaboradorRepository.getById(id);
        if (isNull(entity)) {
            throw new NotFoundException("Colaborador produção não encontrado para id: " + id);
        }
        return ColaboradorProducaoMapper.toResponse(entity);
    }

    public ColaboradorProducaoResponse saveColaborador(ColaboradorProducaoRequest colaborador) {
        ColaboradorProducao entity = ColaboradorProducaoMapper.toEntity(colaborador);
        entity.setTipo(TipoColaborador.PRODUCAO);
        return ColaboradorProducaoMapper.toResponse(colaboradorRepository.save(entity));
    }

    public Map<Integer, ColaboradorProducaoResponse> getAll() {
        return colaboradorRepository.getAll().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ColaboradorProducaoMapper.toResponse(entry.getValue())
                ));
    }

    public ColaboradorProducaoResponse updateColaborador(ColaboradorProducaoRequest request, int id) {
        ColaboradorProducao existente = colaboradorRepository.getById(id);
        if (isNull(existente)) {
            throw new NotFoundException("Colaborador produção não encontrado para id: " + id);
        }

        ColaboradorProducao colaboradorAtualizado = ColaboradorProducao.builder()
                .id(id)
                .nome(request.nome())
                .salario(request.salario())
                .quantidadeProduzida(request.quantidadeProduzida())
                .valorUnidade(request.valorPagoPorUnidade())
                .tipo(existente.getTipo())
                .build();

        return ColaboradorProducaoMapper.toResponse(colaboradorRepository.update(colaboradorAtualizado));
    }

    public void deleteColaborador(int id) {
        colaboradorRepository.delete(id);
    }
}
