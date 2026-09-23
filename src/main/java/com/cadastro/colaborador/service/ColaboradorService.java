package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

// IMPROVE (local only): No DB transactions needed; handle consistency manually if required
@Service
@RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;

    // IMPROVE (local): Add null check and throw custom exception; local list may miss id
    public Colaborador getColaborador(int id) {
        return colaboradorRepository.getColaboradorById(id);
    }

    // IMPROVE (local): Generate id safely (AtomicInteger or max id + 1), not fragile getLast
    public Colaborador saveColaborador(Colaborador colaborador) {
        int id = getAll().getLast().getId() + 1;
        colaborador.setId(id);
        return colaboradorRepository.saveColaborador(colaborador);
    }
    
    // IMPROVE (local): Consider pagination or filtering since all data is in memory
    public List<Colaborador> getAll(){
        return colaboradorRepository.getAll();
    }

    // IMPROVE: Use custom exception (e.g., NotFoundException) instead of NullPointerException
    public Colaborador updateColaborador(Colaborador colaborador, int id) {
        Colaborador colaboradorAtual =  getColaborador(id);

        if(isNull(colaboradorAtual)){
            // IMPROVE (local): Throw custom exception (NotFoundException) instead of NullPointerException
            throw new NullPointerException("Colaborador não encontrado");
        }

        // IMPROVE (local): Preserve tipo and subclass fields; use mapping or copy constructor
        Colaborador colaboradorAtualizado = Colaborador.builder()
                .id(id)
                .nome(colaborador.getNome())
                .salario(colaborador.getSalario())
                .build();

        return colaboradorRepository.update(colaboradorAtualizado);
    }

    // IMPROVE (local): Check existence before deleting; local list removal by object can fail
    public void deleteColaborador(Colaborador colaborador){
        colaboradorRepository.delete(colaborador);
    }
}
