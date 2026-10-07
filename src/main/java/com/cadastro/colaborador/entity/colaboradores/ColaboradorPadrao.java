package com.cadastro.colaborador.entity.colaboradores;


import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "colaborador_padrao")
@EqualsAndHashCode(callSuper = true)
public class ColaboradorPadrao extends Colaborador {
}
