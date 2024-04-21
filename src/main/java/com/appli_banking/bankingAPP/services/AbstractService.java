package com.appli_banking.bankingAPP.services;

import java.util.List;

public interface AbstractService<T> {
    //envoyer que l'ID
    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);
}
