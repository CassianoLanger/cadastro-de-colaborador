package com.cadastro.colaborador.service.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import com.cadastro.colaborador.entity.colaboradores.TipoColaborador;
import com.cadastro.colaborador.exception.NotFoundException;
import com.cadastro.colaborador.mapper.colaboradores.ColaboradorComissionadoMapper;
import com.cadastro.colaborador.repository.colaboradores.ColaboradorComissionadoRepository;
import com.cadastro.colaborador.request.colaboradores.ColaboradorComissionadoRequest;
import com.cadastro.colaborador.response.colaboradores.ColaboradorComissionadoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColaboradorComissionadoService {
    private static final BigDecimal PERCENTUAL = BigDecimal.valueOf(100);

    private final ColaboradorComissionadoRepository colaboradorRepository;

    public ColaboradorComissionadoResponse getColaborador(Long id) {
        return ColaboradorComissionadoMapper.toResponse(findColaborador(id));
    }

    public ColaboradorComissionadoResponse saveColaborador(ColaboradorComissionadoRequest request) {
        ColaboradorComissionado entity = ColaboradorComissionadoMapper.toEntity(request);
        entity.setTipoColaborador(TipoColaborador.COMISSIONADO);
        entity.setComissao(calcularComissao(entity.getValorDasVendas(), entity.getPercentualDeComissao()));
        return ColaboradorComissionadoMapper.toResponse(colaboradorRepository.save(entity));
    }

    public Map<Long, ColaboradorComissionadoResponse> getAll() {
        return colaboradorRepository.findAll().stream()
                .collect(Collectors.toMap(ColaboradorComissionado::getId, ColaboradorComissionadoMapper::toResponse));
    }

    public ColaboradorComissionadoResponse updateColaborador(ColaboradorComissionadoRequest request, Long id) {
        ColaboradorComissionado entity = findColaborador(id);
        entity.setNome(request.nome());
        entity.setMatricula(request.matricula());
        entity.setSalario(request.salario());
        entity.setValorDasVendas(request.valorDasVendas());
        entity.setPercentualDeComissao(request.percentualDeComissao());
        entity.setComissao(calcularComissao(entity.getValorDasVendas(), entity.getPercentualDeComissao()));
        return ColaboradorComissionadoMapper.toResponse(colaboradorRepository.save(entity));
    }

    public void deleteColaborador(Long id) {
        if (!colaboradorRepository.existsById(id)) {
            throw notFound(id);
        }
        colaboradorRepository.deleteById(id);
    }

    private ColaboradorComissionado findColaborador(Long id) {
        return colaboradorRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private NotFoundException notFound(Long id) {
        return new NotFoundException("Colaborador comissionado não encontrado para id: " + id);
    }

    private BigDecimal calcularComissao(BigDecimal vendas, BigDecimal percentual) {
        return vendas.multiply(percentual).divide(PERCENTUAL, 2, RoundingMode.HALF_UP);
    }
}
