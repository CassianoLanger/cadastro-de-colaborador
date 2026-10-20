package com.cadastro.colaborador.repository.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorProducaoRepository extends JpaRepository<ColaboradorProducao,Long> {

//    @Override
//    public void delete(int id) {
//        colaboradores.remove(id);
//    }
//    void mockColaborador() {
//        colaboradores.put(1, ColaboradorProducao.builder()
//                .id(1)
//                .nome("Carlos")
//                .salario(new BigDecimal("4200"))
//                .tipo(TipoColaborador.PRODUCAO)
//                .quantidadeProduzida(new BigDecimal("100"))
//                .valorUnidade(new BigDecimal("42"))
//                .build());
//    }
}
