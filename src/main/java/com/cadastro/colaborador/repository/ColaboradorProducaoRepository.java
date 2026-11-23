package com.cadastro.colaborador.repository;


import com.cadastro.colaborador.entity.ColaboradorProducao;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Repository
public class ColaboradorProducaoRepository {
    private final List<ColaboradorProducao> colaboradores = new ArrayList<>();

    public ColaboradorProducao salvar(ColaboradorProducao colaborador) {
        if (colaborador.getId() == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        boolean idJaExiste = colaboradores.stream()
                .anyMatch(c -> colaborador.getId().equals(c.getId()));
        if (idJaExiste) {
            throw new IllegalArgumentException("Já existe um colaborador com esse id");
        }
        colaboradores.add(colaborador);
        return colaborador;
    }
    public List<ColaboradorProducao> listarTodos(){
        return colaboradores;
    }

    public Optional<ColaboradorProducao> buscarPorId(Long id) {
        return colaboradores.stream()
                .filter(x -> id.equals(x.getId()))
                .findFirst();
    }

    public Optional<ColaboradorProducao> atualizar(Long id, ColaboradorProducao dadosAtualizados){
        return buscarPorId(id)
                .map(colaborador -> {
                    colaborador.setNome(dadosAtualizados.getNome());
                    colaborador.setSalario(dadosAtualizados.getSalario());
                    colaborador.setQuantidadeProduzida(dadosAtualizados.getQuantidadeProduzida());
                    colaborador.setValorPorUnidade(dadosAtualizados.getValorPorUnidade());
                    return colaborador;
                });
    }

    public boolean deletar(Long id){
        return colaboradores.removeIf(x -> x.getId().equals(id));
    }
}
