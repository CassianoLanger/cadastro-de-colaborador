package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;

    public Colaborador getColaborador(int id) {
        return colaboradorRepository.getColaboradorById(id);
    }

    public Colaborador saveColaborador(Colaborador colaborador) {
        int id = getAll().getLast().getId() + 1;
        colaborador.setId(id);
        return colaboradorRepository.saveColaborador(colaborador);
    }
    
    public List<Colaborador> getAll(){
        return colaboradorRepository.getColaboradores();
    }

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

        return colaboradorRepository.updateColaborador(colaboradorAtualizado);
    }
}
