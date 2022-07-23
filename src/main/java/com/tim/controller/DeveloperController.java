package com.tim.controller;
import com.tim.model.Developer;
import com.tim.service.DeveloperService;

import java.util.List;

public class DeveloperController {

    DeveloperService userService = new DeveloperService();

    public DeveloperController() {
    }

    public Developer getDeveloperById(Long id) {
        Developer developer = new Developer();
        return userService.getById(id);
    }

    public List<Developer> getAllDevelopers() {
        return userService.getAll();
    }

    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    public Developer updateDeveloper(Long id, String firstNewName, String lastNewName) {
        Developer developer = new Developer();
        developer.setId(id);
        userService.update(developer);
    }

    public void insertDeveloper(String firstName, String lastName, String status, int specialty, int skills) {
        userService.insert(firstName, lastName, status, specialty, skills);
    }

}
