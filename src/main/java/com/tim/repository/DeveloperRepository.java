package com.tim.repository;
import java.sql.SQLException;

public class DeveloperRepository extends GenericRepository{

    @Override
    public void getAll() throws SQLException {
        super.getAll();
    }

    @Override
    public void update(String fName, String lName, String newfName, String newlName) throws SQLException {
        super.update(fName, lName, newfName, newlName);
    }

    @Override
    public void insert(String firstName, String lastName, String status, String specialty, String skills) throws SQLException {
        super.insert(firstName, lastName, status, specialty, skills);
    }

    @Override
    public void delete(String fName, String lName) throws SQLException {
        super.delete(fName, lName);
    }

    public DeveloperRepository() throws SQLException {
    }

    public void updateDeveloper(String fName, String lName, String newfName, String newlName) throws SQLException {
        update(fName, lName, newfName, newlName);
    }

    public void deleteDeveloper(String firstName, String lastName) throws SQLException {
        delete(firstName, lastName);
    }

    public void insertDeveloper(String firstName, String lastName, String status, String specialty, String skills) throws SQLException {
        insert(firstName, lastName, status, specialty, skills);
    }

    public void getAllDevelopers() throws SQLException {
        getAll();
    }

}
