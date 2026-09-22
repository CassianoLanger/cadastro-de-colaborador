package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.Colaborador;
import com.cadastro.colaborador.entity.TipoColaborador;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ColaboradorRepository {

    private final List<Colaborador> colaboradores = new ArrayList<>();

    public ColaboradorRepository() {
        colaboradores.add(
                new Colaborador(1L, "João", 2780, TipoColaborador.PADRAO)
        );

        colaboradores.add(
                new Colaborador(2L, "Maria", 12333, TipoColaborador.COMISSIONADO)
        );

        colaboradores.add(
                new Colaborador(3L, "Carlos", 4200, TipoColaborador.PRODUCAO)
        );
    }

    public Colaborador getColaboradorById(int id) {
        return colaboradores.get(id - 1);
    }
}