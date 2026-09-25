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

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;

    // REV: getColaborador pode lançar NPE se id não existir; considerar NotFoundException
    public ColaboradorResponse getColaborador(int id) {
        return ColaboradorMapper.toResponse(colaboradorRepository.getById(id));
    }

    // REV: saveColaborador não valida request (ex: nome vazio); considerar @Valid no controller
    public ColaboradorResponse saveColaborador(ColaboradorRequest colaborador) {
        return ColaboradorMapper.toResponse(colaboradorRepository.saveColaborador(colaborador));
    }

    // REV: getAll() carrega tudo em memória; considerar paginação para grandes volumes
    public Map<Integer, ColaboradorResponse> getAll(){
        return colaboradorRepository.getAll().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ColaboradorMapper.toResponse(entry.getValue())
                ));
    }

    // REV: updateColaborador usa NullPointerException; ideal seria exceção personalizada (NotFoundException)
    // REV: mapeia request para entidade (colaboradorAtual) mas não usa o objeto mapeado diretamente (só verifica null)
    public ColaboradorResponse updateColaborador(ColaboradorRequest request, int id) {

        ColaboradorResponse colaborador = getColaborador(id);
        Colaborador colaboradorAtual =  ColaboradorMapper.toEntity(request);

        if(isNull(colaboradorAtual) || isNull(colaborador)){
            throw new NullPointerException("Colaborador não encontrado");
        }

        // REV: tipo é forçado a PADRAO; se o request tiver tipo, deveria ser preservado
        Colaborador colaboradorAtualizado = Colaborador.builder()
                .id(id)
                .nome(request.nome())
                .salario(request.salario())
                .tipo(TipoColaborador.PADRAO)
                .build();

        return ColaboradorMapper.toResponse(colaboradorRepository.update(colaboradorAtualizado));
    }

    public void deleteColaborador(int id){
        colaboradorRepository.delete(id);
    }
}
