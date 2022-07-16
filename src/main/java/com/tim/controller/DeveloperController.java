package com.tim.controller;
import com.tim.service.UserService;

public class DeveloperController {

    UserService userService = new UserService();

    public DeveloperController() {
    }

    public void saveDevelopers()  {
        userService.save();
    }

    public void getAllDevelopers() {
        userService.getAll();
    }

    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    public void updateDeveloper(Long id, String firstNewName, String lastNewName) {
        userService.update(id, firstNewName, lastNewName);
    }

    public void insertDeveloper(String firstName, String lastName, String status, int specialty, int skills) {
        userService.insert(firstName, lastName, status, specialty, skills);
    }

    public void getAllSpecialtyDeveloper() {
        userService.getAllSpecialty();
    }

    public void getAllSkillsDeveloper() {
        userService.getAllSkills();
    }

}
