package com.cadastro.colaborador.repository;

import java.util.Map;

public interface RepositoryInterface<T>{

    T getById(int id);

    T save(T t);

    Map<Integer,T> getAll();

    T update(T t);

    void delete(int t);
}