package com.tim.controller;

import com.tim.repository.DatabaseRepository;

import java.sql.SQLException;

public class DeveloperController {

    private DatabaseRepository myDevRep = new DatabaseRepository();

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

    public void insertDeveloper(String firstName, String lastName, String status, int specialty, int skills) throws SQLException {
        myDevRep.insert(firstName, lastName, status, specialty, skills);
    }

    public void getAllSpecialtyDeveloper() {
        myDevRep.getAllSpecialty();
    }

    public void getAllSkillsDeveloper() {
        myDevRep.getAllSkills();
    }

    public int getNewIdDeveloper() throws SQLException {
        return myDevRep.getId();
    }
}
