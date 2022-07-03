package com.tim.repository;

import java.sql.*;

public class GenericRepository {

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/datadevelopers";
    static final String USER = "root";
    static final String PASSWORD = "password";

    Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    Statement statement = connection.createStatement();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String sqlQuery;

    public GenericRepository() throws SQLException {
    }

    public void delete(String fName, String lName) throws SQLException {
        sqlQuery = "DELETE FROM developers WHERE FirstName = ? AND LastName = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, fName);
        preparedStatement.setString(2, lName);
        preparedStatement.executeUpdate();
    }

    public ResultSet getAll() throws SQLException {
        sqlQuery = "SELECT * FROM developers";
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;
    }

    public void insert(String firstName, String lastName, String status, String specialty, String skills) throws SQLException {
        sqlQuery = "INSERT INTO developers(id, FirstName, LastName, Status, Specialty, Skills) " +
                   "VALUES (id, ?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, status);
        preparedStatement.setString(4, specialty);
        preparedStatement.setString(5, skills);
        preparedStatement.executeUpdate();
    }

    public void saveChange() throws SQLException {
        resultSet.close();
        statement.close();
        preparedStatement.close();
        connection.close();
    }
}
