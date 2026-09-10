package com.cadastro.colaborador.repository;

import com.cadastro.colaborador.entity.Colaborador;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class ColaboradorRepository {

    private final List<Colaborador> colaboradores = new ArrayList<>();

    public ColaboradorRepository() {
        colaboradores.add(
                new Colaborador(0, "João", 2780)
        );

        colaboradores.add(
                new Colaborador(1, "Maria", 12333)
        );

        colaboradores.add(
                new Colaborador(2, "Carlos", 4200)
        );
    }

    public Colaborador getColaboradorById(int id) {
        return colaboradores.stream()
                .filter(colaborador -> colaborador.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("Colaborador com id: " + id + " não existe"));
    }

    public Colaborador saveColaborador(Colaborador colaborador) {
        colaboradores.add(colaborador);
        return colaboradores.get(colaborador.getId());
    }

    public Colaborador updateColaborador(Colaborador colaborador){
        colaboradores.add(colaborador.getId(), colaborador);
        return colaboradores.get(Math.toIntExact(colaborador.getId()));
    }
}