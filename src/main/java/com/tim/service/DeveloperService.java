package com.tim.service;
import com.tim.repository.DeveloperRepository;
import java.sql.*;

public class DeveloperService implements DeveloperRepository {

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/datadevelopers";
    static final String USER = "root";
    static final String PASSWORD = "password";

    Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    Statement statement = connection.createStatement();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String sqlQuery;

    public DeveloperService() throws SQLException {
    }

    @Override
    public void save() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }

    @Override
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

    @Override
    public void deleteById(Long id) throws SQLException {
        sqlQuery = "DELETE FROM developers " +
                   "WHERE id = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Long id, String firstNewName, String lastNewName) throws SQLException {
        sqlQuery = "UPDATE developers " +
                   "SET FirstName = ?, LastName = ?" +
                   "WHERE id = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1 ,firstNewName);
        preparedStatement.setString(2 ,lastNewName);
        preparedStatement.setLong(3 ,id);
        preparedStatement.executeUpdate();
    }

    @Override
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
}
