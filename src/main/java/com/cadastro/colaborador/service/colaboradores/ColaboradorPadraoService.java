package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.Colaborador;
import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.exception.NotFoundException;
import com.cadastro.colaborador.mapper.colaboradores.ColaboradorMapper;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorRequest;
import com.cadastro.colaborador.request.paycheck.FolhaDePagamentoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorResponse;
import com.cadastro.colaborador.service.paycheck.FolhaDePagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorPadraoService {
    private final ColaboradorPadraoRepository colaboradorRepository;
    private final FolhaDePagamentoService folhaDePagamentoService;

    public ColaboradorResponse getColaborador(int id) {
        ColaboradorPadrao entity = colaboradorRepository.getById(id);
        if (isNull(entity)) {
            throw new NotFoundException("Colaborador não encontrado para id: " + id);
        }
        return ColaboradorMapper.toResponse(entity);
    }

    public ColaboradorResponse saveColaborador(ColaboradorRequest colaborador) {
        ColaboradorPadrao entity = ColaboradorMapper.toEntity(colaborador);
        entity.setTipo(TipoColaborador.PADRAO);
        folhaDePagamentoService.salvarFolha(new FolhaDePagamentoRequest(1, colaborador.matricula(), colaborador.salario()));
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

        ColaboradorPadrao colaboradorAtualizado = ColaboradorPadrao.builder()
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
