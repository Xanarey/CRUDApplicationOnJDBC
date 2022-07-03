package com.tim.view;

import com.tim.repository.DeveloperRepository;

import java.sql.*;

public class DevelopersJDBC {

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/datadevelopers";
    static final String USER = "root";
    static final String PASSWORD = "password";
    DeveloperRepository developerRepository = new DeveloperRepository();

    public DevelopersJDBC() throws SQLException {
    }

    public void startingJDBC() throws SQLException {

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        String sqlSELECT = "SELECT * FROM developers";

        ResultSet resultSet = statement.executeQuery(sqlSELECT);
        System.out.println("Retrieving data from database...");
        developerRepository.getAllDevelopers();

        resultSet.close();
        statement.close();
        connection.close();
    }

}
