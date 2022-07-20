package com.tim.repository;

import java.util.List;

public interface GeneralRepository<T, ID> {

    T getById(ID id);
    void save();
    List<T> getAll();
    void deleteById(Long id);
    void update(Long id, String firstNewName, String lastNewName);
    void insert(String firstName, String lastName, String status, int specialty, int skills);

}
