package com.cadastro.colaborador.repository.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorPadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorPadraoRepository extends JpaRepository<ColaboradorPadrao, Long> {

//    void mockColaboradores() {
//        ColaboradorPadrao colaboradorPadrao = ColaboradorPadrao.builder()
//                .id(1)
//                .matricula("JM1")
//                .nome("Joao Moraes")
//                .salario(new BigDecimal("2700"))
//                .tipo(TipoColaborador.PADRAO)
//                .build();
//        colaboradores.put(1,colaboradorPadrao);
//    }
}