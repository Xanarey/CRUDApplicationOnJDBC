package com.tim.repository;

import java.util.List;

public interface GeneralRepository<T, ID> {

    T getById(ID id);
    List<T> getAll();
    void deleteById(Long id);
    T update(T t);
    T insert(T t);

}
