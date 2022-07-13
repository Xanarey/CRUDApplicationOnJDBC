package com.tim.controller;

import com.tim.repository.DatabaseRepository;
import com.tim.service.UserService;

import java.sql.SQLException;

public class DeveloperController {

    UserService userService = new UserService();

    public DeveloperController() throws SQLException {
    }

    public void saveDevelopers() throws SQLException {
        userService.save();
    }

    public void getAllDevelopers() throws SQLException {
        userService.getAll();
    }

    public void deleteById(Long id) throws SQLException {
        userService.deleteById(id);
    }

    public void updateDeveloper(Long id, String firstNewName, String lastNewName) throws SQLException {
        userService.update(id, firstNewName, lastNewName);
    }

    public void insertDeveloper(String firstName, String lastName, String status, int specialty, int skills) throws SQLException {
        userService.insert(firstName, lastName, status, specialty, skills);
    }

    public void getAllSpecialtyDeveloper() {
        userService.getAllSpecialty();
    }

    public void getAllSkillsDeveloper() {
        userService.getAllSkills();
    }

}
