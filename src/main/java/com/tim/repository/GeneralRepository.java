package com.tim.repository;

public interface GeneralRepository {

    void save();
    void getAll();
    void deleteById(Long id);
    void update(Long id, String firstNewName, String lastNewName);
    void insert(String firstName, String lastName, String status, int specialty, int skills);

}
