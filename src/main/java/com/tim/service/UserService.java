package com.tim.service;

import com.tim.repository.DatabaseRepository;
import com.tim.repository.DeveloperRepository;

import java.sql.SQLException;

public class UserService implements DeveloperRepository {

    DatabaseRepository databaseRepository = new DatabaseRepository();

    public UserService() throws SQLException {
    }

    @Override
    public void save() throws SQLException {
        databaseRepository.save();
    }

    @Override
    public void getAll() throws SQLException {
        databaseRepository.getAll();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        databaseRepository.deleteById(id);
    }

    @Override
    public void update(Long id, String firstNewName, String lastNewName) throws SQLException {
        databaseRepository.update(id, firstNewName, lastNewName);
    }

    @Override
    public void insert(String firstName, String lastName, String status, int specialty, int skills) throws SQLException {
        databaseRepository.insert(firstName, lastName, status, specialty, skills);
    }


    public void getAllSpecialty() {
        databaseRepository.getAllSpecialty();
    }

    public void getAllSkills() {
        databaseRepository.getAllSkills();
    }
}
