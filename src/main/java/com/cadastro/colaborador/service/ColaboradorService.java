package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.TipoColaborador;
import com.cadastro.colaborador.mapper.ColaboradorMapper;
import com.cadastro.colaborador.repository.ColaboradorRepository;
import com.cadastro.colaborador.request.ColaboradorRequest;
import com.cadastro.colaborador.response.ColaboradorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import com.cadastro.colaborador.exception.NotFoundException;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorResponse getColaborador(int id) {
        Colaborador entity = colaboradorRepository.getById(id);
        if (isNull(entity)) {
            throw new NotFoundException("Colaborador não encontrado para id: " + id);
        }
        return ColaboradorMapper.toResponse(entity);
    }

    public ColaboradorResponse saveColaborador(ColaboradorRequest colaborador) {
        Colaborador entity = ColaboradorMapper.toEntity(colaborador);
        entity.setTipo(TipoColaborador.PADRAO);
        return ColaboradorMapper.toResponse(colaboradorRepository.save(entity));
    }

    public Map<Integer, ColaboradorResponse> getAll(){
        return colaboradorRepository.getAll().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ColaboradorMapper.toResponse(entry.getValue())
                ));
    }

    public ColaboradorResponse updateColaborador(ColaboradorRequest request, int id) {
        Colaborador existente = colaboradorRepository.getById(id);
        if (isNull(existente)) {
            throw new NotFoundException("Colaborador não encontrado para id: " + id);
        }

        Colaborador colaboradorAtualizado = Colaborador.builder()
                .id(id)
                .nome(request.nome())
                .salario(request.salario())
                .tipo(existente.getTipo())
                .build();

        return ColaboradorMapper.toResponse(colaboradorRepository.update(colaboradorAtualizado));
    }

    public void deleteColaborador(int id){
        colaboradorRepository.delete(id);
    }
}
