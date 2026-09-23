package com.cadastro.colaborador.repository;

import java.util.List;

public interface RepositoryInterface<T>{

    T getColaboradorById(int id);

    T saveColaborador(T t);

    List<T> getAll();

    T update(T t);

    void delete(T t);
}