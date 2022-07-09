package com.tim.service;
import com.tim.repository.DeveloperRepository;
import java.sql.*;

public class DeveloperService implements DeveloperRepository {

    //static final String DATABASE_URL = "jdbc:mysql://localhost:3306/datadevelopers";
    static final String DATABASE_URL = "jdbc:sqlite:identifier.sqlite";
    static final String USER = "";
    static final String PASSWORD = "";



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
    public void getAll() {

        sqlQuery = "SELECT developers_skills.id AS id, developers.id AS developer_id," +
                   "developers.firstName AS firstName, developers.lastName AS lastName, developers.status AS status, specialty.name AS specialty, skills.name AS skill\n" +
                   "FROM developers_skills LEFT JOIN developers ON developers_skills.developers_id = developers.id\n" +
                   "LEFT JOIN specialty ON developers.specialty_id = specialty.id LEFT JOIN skills ON developers_skills.skills_id = skills.id;";
        try {
            resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nDevelopers:");
            while (resultSet.next()) {

                int devId = resultSet.getInt("id");
                int id = resultSet.getInt("developer_id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String status = resultSet.getString("status");
                String specialty = resultSet.getString("specialty");
                String skill = resultSet.getString("skill");

                System.out.println("\n================\n");
                System.out.println("id: " + devId);
                System.out.println("developer_id: " + id);
                System.out.println("firstName: " + firstName);
                System.out.println("lastName: " + lastName);
                System.out.println("status: " + status);
                System.out.println("specialty: " + specialty);
                System.out.println("skill: " + skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        sqlQuery = "UPDATE developers SET status = 'DELETED' WHERE id = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Long id, String firstNewName, String lastNewName) throws SQLException {
        sqlQuery = "UPDATE developers SET firstName = ?, lastName = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1 ,firstNewName);
        preparedStatement.setString(2 ,lastNewName);
        preparedStatement.setLong(3 ,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void insert(String firstName, String lastName, String status, int specialty, int skills) throws SQLException {

        sqlQuery = "INSERT INTO developers(firstName, lastName, status, specialty_id) " +
                   "VALUES (?,?,?,?)";


        connection.setAutoCommit(false);

        // TODO СДЕЛАТЬ ЧТОБЫ ДОБАВЛЯЛОСЬ И В developers_skills тоже....

        preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, status);
        preparedStatement.setInt(4, specialty);
        preparedStatement.addBatch(sqlQuery);

        preparedStatement.executeBatch();

        connection.commit();

        connection.setAutoCommit(true);

        //preparedStatement.executeUpdate();
    }

    public void getAllSpecialty() {
        sqlQuery = "SELECT * FROM specialty";
        try {
            resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nSpecialty:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllSkills() {
        sqlQuery = "SELECT * FROM skills";
        try {
            resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nSkills:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
