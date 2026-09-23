package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.repository.ColaboradorComissionadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.Objects.isNull;

// IMPROVE (local only): Should use ColaboradorComissionadoRepository for local in-memory storage
// IMPROVE (local): Should return ColaboradorComissionado and handle production fields locally
@Service
@RequiredArgsConstructor
public class ColaboradorComissionadoService {

    private final ColaboradorComissionadoRepository colaboradorRepository;

    public ColaboradorComissionado getColaborador(int id) {
        return colaboradorRepository.getColaboradorById(id);
    }

    public ColaboradorComissionado saveColaborador(ColaboradorComissionado colaborador) {
        int id = new ServiceUtils<ColaboradorComissionado>().newId(getAll());
        colaborador.setId(id);
        return colaboradorRepository.saveColaborador(colaborador);
    }
    
    public Map<Integer, ColaboradorComissionado> getAll(){
        return colaboradorRepository.getAll();
    }

    // IMPROVE (local): Preserve subclass fields (valorDasVendas, percentualDeComissao) locally
    public ColaboradorComissionado updateColaborador(ColaboradorComissionado colaborador, int id) {
        Colaborador colaboradorAtual =  getColaborador(id);

        if(isNull(colaboradorAtual)){
            throw new NullPointerException("Colaborador não encontrado");
        }

        ColaboradorComissionado colaboradorAtualizado = ColaboradorComissionado.builder()
                .id(id)
                .nome(colaborador.getNome())
                .salario(colaborador.getSalario())
                .build();

        return colaboradorRepository.update(colaboradorAtualizado);
    }

    public void deleteColaborador(ColaboradorComissionado colaborador) {
        colaboradorRepository.delete(colaborador);
    }
}
