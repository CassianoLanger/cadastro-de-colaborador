package com.cadastro.colaborador.service;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;
    public Colaborador getColaborador(int id) {
        return colaboradorRepository.getColaboradorById(id);
    }

    public Colaborador saveColaborador(Colaborador colaraboradorRequest) {
        return null;
    }
}
