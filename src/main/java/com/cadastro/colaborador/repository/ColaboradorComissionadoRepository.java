package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.ColaboradorComissionado;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ColaboradorComissionadoRepository {

    private final List<ColaboradorComissionado> colaboradores = new ArrayList<>();

    public List<ColaboradorComissionado> buscarTodos() {
        return colaboradores;
    }

    public Optional<ColaboradorComissionado> buscarPorId(Long id) {
        return colaboradores.stream().filter(colaborador -> colaborador.getId().equals(id)).findFirst();
    }

    public ColaboradorComissionado salvar(ColaboradorComissionado colaborador) {
        colaboradores.add(colaborador);
        return colaborador;
    }

    public void remover(ColaboradorComissionado colaborador) {
        colaboradores.remove(colaborador);
    }
}
