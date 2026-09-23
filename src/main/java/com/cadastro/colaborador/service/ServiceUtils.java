package com.cadastro.colaborador.service;

import java.util.Map;
public class ServiceUtils <T>{

    public int newId(Map<Integer, T> t){
        return t.keySet().stream().max(Integer::compareTo).orElse(0) +1;
    }
}
