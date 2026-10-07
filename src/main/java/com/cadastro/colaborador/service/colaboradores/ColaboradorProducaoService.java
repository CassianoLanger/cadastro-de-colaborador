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

@Service
@RequiredArgsConstructor
public class ColaboradorProducaoService {
    private final ColaboradorProducaoRepository colaboradorRepository;

    public ColaboradorProducaoResponse getColaborador(Long id) {
        return ColaboradorProducaoMapper.toResponse(findColaborador(id));
    }

    public ColaboradorProducaoResponse saveColaborador(ColaboradorProducaoRequest request) {
        ColaboradorProducao entity = ColaboradorProducaoMapper.toEntity(request);
        entity.setTipoColaborador(TipoColaborador.PRODUCAO);
        return ColaboradorProducaoMapper.toResponse(colaboradorRepository.save(entity));
    }

    public Map<Long, ColaboradorProducaoResponse> getAll() {
        return colaboradorRepository.findAll().stream()
                .collect(Collectors.toMap(ColaboradorProducao::getId, ColaboradorProducaoMapper::toResponse));
    }

    public ColaboradorProducaoResponse updateColaborador(ColaboradorProducaoRequest request, Long id) {
        ColaboradorProducao entity = findColaborador(id);
        entity.setNome(request.nome());
        entity.setMatricula(request.matricula());
        entity.setSalario(request.salario());
        entity.setQuantidadeProduzida(request.quantidadeProduzida());
        entity.setValorUnidade(request.valorPagoPorUnidade());
        return ColaboradorProducaoMapper.toResponse(colaboradorRepository.save(entity));
    }

    public void deleteColaborador(Long id) {
        if (!colaboradorRepository.existsById(id)) {
            throw notFound(id);
        }
        colaboradorRepository.deleteById(id);
    }

    private ColaboradorProducao findColaborador(Long id) {
        return colaboradorRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private NotFoundException notFound(Long id) {
        return new NotFoundException("Colaborador de produção não encontrado para id: " + id);
    }
}
