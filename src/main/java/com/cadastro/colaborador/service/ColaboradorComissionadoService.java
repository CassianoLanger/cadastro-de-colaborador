package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.ColaboradorComissionado;
import com.cadastro.colaborador.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

// IMPROVE (local only): Should use ColaboradorComissionadoRepository for local in-memory storage
// IMPROVE (local): Should return ColaboradorComissionado and handle production fields locally
@Service
@RequiredArgsConstructor
public class ColaboradorComissionadoService {
    // IMPROVE (local): Inject ComissionadoRepository; avoid duplicating base logic
    private final ColaboradorRepository colaboradorRepository;

    // IMPROVE (local): Avoid duplicating logic; use inheritance/composition for local services
    public Colaborador getColaborador(int id) {
        return colaboradorRepository.getColaboradorById(id);
    }

    // IMPROVE (local): Use specific local repository and preserve subclass fields
    public Colaborador saveColaborador(Colaborador colaborador) {
        int id = getAll().getLast().getId() + 1;
        colaborador.setId(id);
        return colaboradorRepository.saveColaborador(colaborador);
    }
    
    public List<Colaborador> getAll(){
        return colaboradorRepository.getAll();
    }

    // IMPROVE (local): Preserve subclass fields (valorDasVendas, percentualDeComissao) locally
    public Colaborador updateColaborador(Colaborador colaborador, int id) {
        Colaborador colaboradorAtual =  getColaborador(id);

        if(isNull(colaboradorAtual)){
            throw new NullPointerException("Colaborador não encontrado");
        }

        Colaborador colaboradorAtualizado = Colaborador.builder()
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
