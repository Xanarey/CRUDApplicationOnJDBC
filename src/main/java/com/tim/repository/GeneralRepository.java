package com.tim.repository;

import java.sql.SQLException;

public interface GeneralRepository {

    void save() throws SQLException;
    void getAll() throws SQLException;
    void deleteById(Long id) throws SQLException;
    void update(Long id, String firstNewName, String lastNewName) throws SQLException;
    void insert(String firstName, String lastName, String status, int specialty, int skills) throws SQLException;

}
