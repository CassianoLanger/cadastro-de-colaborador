package com.cadastro.colaborador.repository;

import java.util.Map;

public interface RepositoryInterface<T>{

    T getColaboradorById(int id);

    T saveColaborador(T t);

    Map<Integer,T> getAll();

    T update(T t);

    void delete(T t);
}