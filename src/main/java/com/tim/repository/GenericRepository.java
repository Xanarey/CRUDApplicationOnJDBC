package com.tim.repository;

import java.sql.*;

public class GenericRepository {

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/datadevelopers";
    static final String USER = "root";
    static final String PASSWORD = "password";

    Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    Statement statement = connection.createStatement();
    ResultSet resultSet;
    String sqlQuery;

    public GenericRepository() throws SQLException {
    }

    public ResultSet getAll() throws SQLException {
        sqlQuery = "SELECT * FROM developers";
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;
    }

    public void insert(String firstName, String lastName, String status, String specialty, String skills) throws SQLException {
        sqlQuery = "INSERT INTO developers(id, FirstName, LastName, Status, Specialty, Skills) VALUES " +
                "("+ getId() +","+firstName+","+lastName+","+status+","+specialty+","+skills+")";
        statement.addBatch(sqlQuery);
    }

    public ResultSet getId() throws SQLException {
        sqlQuery = "SELECT id FROM developers ORDER BY id DESC LIMIT 1";
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;
    }

}
