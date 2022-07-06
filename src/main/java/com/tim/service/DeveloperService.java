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

    @Override                                             // TODO утечка ресурсов!
    public void save() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }

    @Override
    public void getAll() throws SQLException {
        sqlQuery = "SELECT developers_skills.id AS id, developers_id AS developer_id," +
                   "developer.firstName AS firstName, developer.lastName AS lastName, status, specialty.name AS specialty, skills.name AS skill\n" +
                   "FROM developers_skills LEFT JOIN developer ON developers_skills.developers_id = developer.id\n" +
                   "LEFT JOIN specialty ON developer.specialty_id = specialty.id LEFT JOIN skills ON developers_skills.skills_id = skills.id;";
        resultSet = statement.executeQuery(sqlQuery);
        System.out.println("\nDevelopers:");
        while (resultSet.next()) {

            int devid = resultSet.getInt("developers_skills.id");
            int id = resultSet.getInt("developers_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String status = resultSet.getString("status");
            String specialty = resultSet.getString("specialty");
            String skill = resultSet.getString("skill");

            System.out.println("\n================\n");
            System.out.println("devid: " + devid);
            System.out.println("id: " + id);
            System.out.println("firstName: " + firstName);
            System.out.println("lastName: " + lastName);
            System.out.println("status: " + status);
            System.out.println("specialty_name: " + specialty);
            System.out.println("skill_name: " + skill);
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        sqlQuery = "DELETE FROM developer WHERE id = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Long id, String firstNewName, String lastNewName) throws SQLException {
        sqlQuery = "UPDATE developer SET firstName = ?, lastName = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1 ,firstNewName);
        preparedStatement.setString(2 ,lastNewName);
        preparedStatement.setLong(3 ,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void insert(String firstName, String lastName, String status, String specialty, String skills) throws SQLException {
        sqlQuery = "INSERT INTO developer(id, firstName, lastName, status) " +
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
