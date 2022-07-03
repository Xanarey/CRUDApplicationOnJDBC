package com.tim.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeveloperRepository extends GenericRepository{

    ResultSet resultSet;

    public DeveloperRepository() throws SQLException {
    }

    @Override
    public ResultSet getAll() throws SQLException {
        return super.getAll();
    }

    @Override
    public void insert(int id, String firstName, String lastName, String status, String specialty, String skills) throws SQLException {
        super.insert(id, firstName, lastName, status, specialty, skills);
    }

    @Override
    public ResultSet getId() throws SQLException {
        return super.getId();
    }

    public void insertDeveloper(String firstName, String lastName, String status, String specialty, String skills) throws SQLException {
        insert(getNewIdDeveloper(), firstName, lastName, status, specialty, skills);
    }

    public void getAllDevelopers() throws SQLException {
        resultSet = getAll();
        System.out.println("\nDevelopers:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String FirstName = resultSet.getString("FirstName");
            String LastName = resultSet.getString("LastName");
            String status = resultSet.getString("status");
            String specialty = resultSet.getString("specialty");
            String skills = resultSet.getString("skills");

            System.out.println("\n================\n");
            System.out.println("developers_id: " + id);
            System.out.println("FirstName: " + FirstName);
            System.out.println("LastName: " + LastName);
            System.out.println("status: " + status);
            System.out.println("specialty: " + specialty);
            System.out.println("skills: " + skills);
        }
    }

    public int getNewIdDeveloper() throws SQLException {
        resultSet = getId();
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id + 1;
    }
}