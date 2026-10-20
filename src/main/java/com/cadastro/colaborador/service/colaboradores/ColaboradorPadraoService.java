package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.exception.NotFoundException;
import com.cadastro.colaborador.mapper.colaboradores.ColaboradorMapper;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorPadraoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColaboradorPadraoService {
    private final ColaboradorPadraoRepository colaboradorRepository;

    public ColaboradorResponse getColaborador(Long id) {
        return ColaboradorMapper.toResponse(findColaborador(id));
    }

    public ColaboradorResponse saveColaborador(ColaboradorRequest request) {
        ColaboradorPadrao entity = ColaboradorMapper.toEntity(request);
        entity.setTipoColaborador(TipoColaborador.PADRAO);
        return ColaboradorMapper.toResponse(colaboradorRepository.save(entity));
    }

    public Map<Long, ColaboradorResponse> getAll() {
        return colaboradorRepository.findAll().stream()
                .collect(Collectors.toMap(ColaboradorPadrao::getId, ColaboradorMapper::toResponse));
    }

    public ColaboradorResponse updateColaborador(ColaboradorRequest request, Long id) {
        ColaboradorPadrao entity = findColaborador(id);
        entity.setNome(request.nome());
        entity.setMatricula(request.matricula());
        entity.setSalario(request.salario());
        return ColaboradorMapper.toResponse(colaboradorRepository.save(entity));
    }

    public void deleteColaborador(Long id) {
        if (!colaboradorRepository.existsById(id)) {
            throw notFound(id);
        }
        colaboradorRepository.deleteById(id);
    }

    private ColaboradorPadrao findColaborador(Long id) {
        return colaboradorRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private NotFoundException notFound(Long id) {
        return new NotFoundException("Colaborador padrão não encontrado para id: " + id);
    }
}
