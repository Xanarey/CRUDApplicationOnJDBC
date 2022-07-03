package com.tim.controller;

import com.tim.repository.mysql.MySQLDeveloperRepository;

import java.sql.SQLException;

public class DeveloperController {

    private MySQLDeveloperRepository myDevRep = new MySQLDeveloperRepository();

    public DeveloperController() throws SQLException {
    }

    public void saveDevelopers() throws SQLException {
        myDevRep.save();
    }

    public void getAllDevelopers() throws SQLException {
        myDevRep.getAll();
    }

    public void deleteById(Long id) throws SQLException {
        myDevRep.deleteById(id);
    }

    public void updateDeveloper(Long id, String firstNewName, String lastNewName) throws SQLException {
        myDevRep.update(id, firstNewName, lastNewName);
    }

    public void insertDeveloper(String firstName, String lastName, String status, String specialty, String skills) throws SQLException {
        myDevRep.insert(firstName, lastName, status, specialty, skills);
    }
}
