package com.cadastro.colaborador.repository.colaboradores;

import com.cadastro.colaborador.entity.colaboradores.ColaboradorComissionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorComissionadoRepository extends JpaRepository<ColaboradorComissionado,Long> {

//    void mockColaboradores() {
//        colaboradores.put(1, ColaboradorComissionado.builder()
//                .id(1)
//                .nome("Maria")
//                .salario(new BigDecimal("12333"))
//                .tipo(TipoColaborador.COMISSIONADO)
//                .valorDasVendas(5000)
//                .percentualDeComissao(10)
//                .build());
//    }
}
