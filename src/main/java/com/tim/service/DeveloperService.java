package com.tim.service;

import com.tim.model.Developer;
import com.tim.repository.JdbcDeveloperRepositoryImpl;
import com.tim.repository.DeveloperRepository;

import java.util.List;

public class DeveloperService {

    DeveloperRepository developerRepository = new JdbcDeveloperRepositoryImpl();

    public DeveloperService() {
    }

    public Developer getById(Long aLong) {
        return developerRepository.getById(aLong);
    }

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }

    public Developer update(Developer developer) {
        return developerRepository.update(developer);
    }

    public Developer insert(String firstName, String lastName, String status, int specialty, int skills) {
        Developer developer = new Developer();
        return developerRepository.insert(developer);
    }
}
