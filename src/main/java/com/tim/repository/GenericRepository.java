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

    public void update(String fName, String lName, String newfName, String newlName) throws SQLException {
        sqlQuery = "UPDATE developers " +
                   "SET FirstName = ?, LastName = ?" +
                   "WHERE FirstName = ? AND LastName = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1 ,newfName);
        preparedStatement.setString(2 ,newlName);
        preparedStatement.setString(3 ,fName);
        preparedStatement.setString(4 ,lName);
        preparedStatement.executeUpdate();
    }

    public void delete(String fName, String lName) throws SQLException {
        sqlQuery = "DELETE FROM developers WHERE FirstName = ? AND LastName = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, fName);
        preparedStatement.setString(2, lName);
        preparedStatement.executeUpdate();
    }

    public void getAll() throws SQLException {
        sqlQuery = "SELECT * FROM developers";
        resultSet = statement.executeQuery(sqlQuery);
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
