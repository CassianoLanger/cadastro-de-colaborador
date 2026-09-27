package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.mapper.colaboradores.ColaboradorComissionadoMapper;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorComissionadoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import com.cadastro.colaborador.exception.NotFoundException;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorComissionadoService {

    private final ColaboradorComissionadoRepository colaboradorRepository;

    public ColaboradorComissionadoResponse getColaborador(int id) {
        ColaboradorComissionado entity = colaboradorRepository.getById(id);
        if (isNull(entity)) {
            throw new NotFoundException("Colaborador comissionado não encontrado para id: " + id);
        }
        return ColaboradorComissionadoMapper.toResponse(entity);
    }

    public ColaboradorComissionadoResponse saveColaborador(ColaboradorComissionadoRequest colaborador) {
        ColaboradorComissionado entity = ColaboradorComissionadoMapper.toEntity(colaborador);
        BigDecimal comissao = BigDecimal.valueOf(entity.getValorDasVendas() * entity.getPercentualDeComissao() / 100.0);
        entity.setTipo(TipoColaborador.COMISSIONADO);
        entity.setComissao(comissao);
        return ColaboradorComissionadoMapper.toResponse(colaboradorRepository.save(entity));
    }

    public Map<Integer, ColaboradorComissionadoResponse> getAll() {
        return colaboradorRepository.getAll().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ColaboradorComissionadoMapper.toResponse(entry.getValue())
                ));
    }

    public ColaboradorComissionadoResponse updateColaborador(ColaboradorComissionadoRequest request, int id) {
        ColaboradorComissionado existente = colaboradorRepository.getById(id);
        if (isNull(existente)) {
            throw new NotFoundException("Colaborador comissionado não encontrado para id: " + id);
        }

        BigDecimal comissao = BigDecimal.valueOf(request.valorDasVendas() * request.percentualDeComissao() / 100.0);

        ColaboradorComissionado colaboradorAtualizado = ColaboradorComissionado.builder()
                .id(id)
                .nome(request.nome())
                .salario(request.salario())
                .valorDasVendas(request.valorDasVendas())
                .percentualDeComissao(request.percentualDeComissao())
                .tipo(existente.getTipo())
                .comissao(comissao)
                .build();

        return ColaboradorComissionadoMapper.toResponse(colaboradorRepository.update(colaboradorAtualizado));
    }

    public void deleteColaborador(int id) {
        colaboradorRepository.delete(id);
    }
}
